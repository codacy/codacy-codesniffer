package codacy.codesniffer.docsgen.parsers

import java.nio.file.Files

import better.files.File
import codacy.codesniffer.docsgen.CategoriesMapper
import com.codacy.plugins.api.results.Pattern.DescriptionText
import com.codacy.plugins.api.results.{Parameter, Pattern, Result}
import com.codacy.tools.scala.seed.utils.CommandRunner

import scala.annotation.tailrec
import scala.util.matching.Regex
import scala.xml.{Elem, NodeSeq, XML}

case class PatternDocs(pattern: Pattern.Specification, description: Pattern.Description, docs: Option[String])

case class PatternIdParts(prefix: String, sniffType: String, patternName: String) {
  val patternId = Pattern.Id(s"${prefix}_${sniffType}_$patternName")
}

trait DocsParser {
  def repositoryURL: String

  def checkoutCommit: String

  def sniffRegex: Regex

  def patternIdPartsFor(relativizedFilePath: String): PatternIdParts

  def descriptionWithDocs(rootDir: File,
                          patternIdParts: PatternIdParts,
                          patternFile: File): (Pattern.Description, Option[String])

  def fallBackCategory: Pattern.Category.Value = Pattern.Category.CodeStyle

  def patterns: Set[PatternDocs] =
    withRepo(repositoryURL, checkoutCommit)(handleRepo)
      .fold(a => throw a, identity)

  @tailrec
  final private def descriptionTrim(description: String): String = {
    if (description.length < 500) {
      description
    } else {
      descriptionTrim(description.substring(0, description.lastIndexOf(".")))
    }
  }

  private[this] def handleRepo(dir: File): Set[PatternDocs] = {
    (for {
      sourceFile <- dir
        .glob(sniffRegex.toString())(File.PathMatcherSyntax.regex)
        .toList
    } yield {
      val idParts = patternIdPartsFor(dir.relativize(sourceFile).toString)

      val (category, subcategory) = CategoriesMapper.categoryFor(idParts, fallBackCategory)

      val parametersList = parseParameters(sourceFile)
      val spec = Pattern.Specification(idParts.patternId,
                                       issueTypeFor(category, sourceFile, Result.Level.Info),
                                       category,
                                       subcategory,
                                       parametersList)

      val (description, docs) = descriptionWithDocs(dir, idParts, sourceFile)
      val parametersDescription = parametersList
        .map(_.map(param => Parameter.Description(param.name, Parameter.DescriptionText(param.name.value))))
      val descriptionWithinLength =
        description.description.map(desc => {
          val descriptionWithoutNewLines = desc.value.replace("\n", " ")
          Pattern.DescriptionText(descriptionTrim(descriptionWithoutNewLines))
        })

      PatternDocs(spec,
                  description.copy(parameters = parametersDescription, description = descriptionWithinLength),
                  docs)
    }).to(Set)
  }

  private[this] def withRepo[A](repositoryURL: String, checkoutCommit: String)(f: File => A): Either[Throwable, A] = {
    val dir = Files.createTempDirectory("")
    for {
      _ <- Right(
        CommandRunner
          .exec(List("git", "clone", repositoryURL, dir.toString))
      )
      _ <- CommandRunner.exec(List("git", "checkout", checkoutCommit), Some(dir.toFile))
      _ <- CommandRunner.exec(List("phpdoc", "-t", "docs", "-d", ".", "--template", "checkstyle,xml,clean"),
                              Some(dir.toFile))

      res = f(dir)
      _ <- CommandRunner.exec(List("rm", "-rf", dir.toString))
    } yield {
      res
    }
  }

  private def parseParameters(patternFile: File): Option[Set[Parameter.Specification]] = {
    val patternRegex = """.*?\spublic.*?\$(.*?)=(.*?);""".r

    Option(patternFile.lineIterator.to(LazyList).collect {
      case patternRegex(name, defaultValue) if !valueIsArray(defaultValue.trim) =>
        Parameter.Specification(Parameter.Name(name.trim), Parameter.Value(defaultValue.trim))
    }).filter(_.nonEmpty)
      .map(_.toSet)
  }

  private def valueIsArray(value: String): Boolean = {
    value.startsWith("array(") || value.startsWith("[")
  }

  private[this] def issueTypeFor(category: Pattern.Category,
                                 patternFile: File,
                                 fallback: Result.Level): Result.Level = {
    Option(category)
      .collect {
        case Pattern.Category.CodeStyle => Result.Level.Info
        case Pattern.Category.Compatibility => Result.Level.Warn
      }
      .orElse(findIssueTypeInSourceFile(patternFile))
      .getOrElse(fallback)
  }

  private[this] def findIssueTypeInSourceFile(patternFile: File): Option[Result.Level] = {
    val errorRegex = """.*->addError\(.*""".r
    val warningRegex = """.*->addWarning\(.*""".r

    patternFile.lineIterator
      .to(LazyList)
      .collectFirst {
        case errorRegex() => Result.Level.Err
        case warningRegex() => Result.Level.Warn
      }
  }

  private[this] def sniffNamespace(namespace: String, patternIdParts: PatternIdParts) = {
    s"$namespace\\${patternIdParts.sniffType}"
  }

  private[this] def sniffClassName(patternIdParts: PatternIdParts) = {
    s"${patternIdParts.patternName}Sniff"
  }

  private[this] def sniffDocumentationInfo(structureXML: Elem, namespace: String, patternIdParts: PatternIdParts) = {
    val patternNamespace = sniffNamespace(namespace, patternIdParts)
    val patternClassName = sniffClassName(patternIdParts)

    for {
      sniffInfo <- (structureXML \ "file" \ "class")
        .find(file => (file \@ "namespace") == patternNamespace && (file \ "name").text == patternClassName)
      docBlock = sniffInfo \ "docblock"
    } yield docBlock
  }

  private[this] def longDescriptionBeautify(docBlock: NodeSeq, classFullNamespace: String) = {
    val longDescription = (docBlock \ "long-description").text
    longDescription
      .stripPrefix(s"$classFullNamespace.")
      .replaceAll("\\.-", ".\n") // for list to start on the next line
      .replaceAll("\\.", ". ") // to add spaces on each paragraph
  }

  private[this] def descriptionBeautify(docBlock: NodeSeq, classFullNamespace: String) = {
    val description = (docBlock \ "description").text
    description.stripPrefix(s"$classFullNamespace.")
  }

  protected def parseExtendedDescription(namespace: String,
                                         patternIdParts: PatternIdParts,
                                         rootDir: File): Option[String] = {
    val docsFile = rootDir / "docs" / "structure.xml"

    val structureXML = XML.loadString(docsFile.contentAsString)

    for {
      docBlock <- this.sniffDocumentationInfo(structureXML, namespace, patternIdParts)
      classFullNamespace = s"\\${sniffNamespace(namespace, patternIdParts)}\\${sniffClassName(patternIdParts)}"
      longDescription = longDescriptionBeautify(docBlock, classFullNamespace)
      description = descriptionBeautify(docBlock, classFullNamespace)
      if longDescription.nonEmpty
    } yield s"$description\n$longDescription".trim
  }

  protected def parseDescription(namespace: String,
                                 patternIdParts: PatternIdParts,
                                 rootDir: File): Option[DescriptionText] = {
    val docsFile = rootDir / "docs" / "structure.xml"

    val structureXML = XML.loadString(docsFile.contentAsString)

    for {
      docBlock <- this.sniffDocumentationInfo(structureXML, namespace, patternIdParts)
      classFullNamespace = s"\\${sniffNamespace(namespace, patternIdParts)}\\${sniffClassName(patternIdParts)}"
      description = descriptionBeautify(docBlock, classFullNamespace)
      if description.nonEmpty
    } yield DescriptionText(description)
  }
}
