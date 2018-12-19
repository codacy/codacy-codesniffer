package codacy.codesniffer.docsgen.parsers

import java.nio.file.Files

import better.files.File
import codacy.codesniffer.docsgen.CategoriesMapper
import com.codacy.plugins.api.results.{Parameter, Pattern, Result}
import com.codacy.tools.scala.seed.utils.CommandRunner

import scala.util.matching.Regex

case class PatternDocs(pattern: Pattern.Specification, description: Pattern.Description, docs: Option[String])

case class PatternIdParts(prefix: String, sniffType: String, patternName: String) {
  val patternId = Pattern.Id(s"${prefix}_${sniffType}_$patternName")
}

trait DocsParser {
  def repositoryURL: String

  def checkoutCommit: String

  def sniffRegex: Regex

  def patternIdPartsFor(relativizedFilePath: String): PatternIdParts

  def descriptionWithDocs(rootDir: File, patternIdParts: PatternIdParts, patternFile: File): (Pattern.Description, Option[String])

  def fallBackCategory: Pattern.Category.Value = Pattern.Category.CodeStyle

  def patterns: Set[PatternDocs] =
    withRepo(repositoryURL, checkoutCommit)(handleRepo)
      .fold(a => throw a, identity)

  private[this] def handleRepo(dir: File): Set[PatternDocs] = {
    (for {
      sourceFile <- dir
        .glob(sniffRegex.toString())(File.PathMatcherSyntax.regex)
        .toList
    } yield {
      val idParts = patternIdPartsFor(dir.relativize(sourceFile).toString)

      val category = CategoriesMapper.categoryFor(idParts, fallBackCategory)

      val spec = Pattern.Specification(idParts.patternId,
                                       issueTypeFor(category, sourceFile, Result.Level.Info),
                                       CategoriesMapper.categoryFor(idParts, fallBackCategory),
                                       parseParameters(sourceFile))

      val (description, docs) = descriptionWithDocs(dir, idParts, sourceFile)

      PatternDocs(spec, description, docs)
    })(collection.breakOut)
  }

  private[this] def withRepo[A](repositoryURL: String, checkoutCommit: String)(f: File => A): Either[Throwable, A] = {
    val dir = Files.createTempDirectory("")
    for {
      _ <- CommandRunner
        .exec(List("git", "clone", repositoryURL, dir.toString))
        .right
      _ <- CommandRunner.exec(List("git", "checkout", checkoutCommit), Some(dir.toFile))
      res = f(dir)
      _ <- CommandRunner.exec(List("rm", "-rf", dir.toString)).right
    } yield {
      res
    }
  }

  private def parseParameters(patternFile: File): Option[Set[Parameter.Specification]] = {
    val patternRegex = """.*?\spublic.*?\$(.*?)=(.*?);""".r

    Option(patternFile.lineIterator.toStream.collect {
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

    patternFile.lineIterator.toStream
      .collectFirst {
        case errorRegex() => Result.Level.Err
        case warningRegex() => Result.Level.Warn
      }
  }
}
