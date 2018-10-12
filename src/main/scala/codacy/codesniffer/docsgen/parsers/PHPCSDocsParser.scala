package codacy.codesniffer.docsgen.parsers

import better.files.File
import com.codacy.plugins.api.results.{Pattern, Result}

import scala.xml.XML

class PHPCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/squizlabs/PHP_CodeSniffer.git"

  private val sniffRegex = """.*src\/Standards\/(.*?)\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  def handleRepo(dir: File): Set[PatternDocs] = {
    (for {
      file <- dir
        .glob(s"$sniffRegex")(File.PathMatcherSyntax.regex)
        .toList
    } yield {
      val sniffRegex(standard, sniffType, patternName) = dir
        .relativize(file)
        .toString
      handlePattern(dir, file, standard, sniffType, patternName)
    }).toSet
  }

  private def handlePattern(rootDir: File,
                            sourceFile: File,
                            standard: String,
                            sniffType: String,
                            patternName: String): PatternDocs = {
    val patternId = Pattern.Id(s"${standard}_${sniffType}_$patternName")
    val spec = Pattern.Specification(patternId,
                                     findIssueType(sourceFile).getOrElse(Result.Level.Warn),
                                     getCategory(patternId),
                                     parseParameters(sourceFile))

    val docsFile = rootDir / "src/Standards" / standard / "Docs" / sniffType / s"${patternName}Standard.xml"
    val (doc, description) =
      if (docsFile.exists) parseDocsFile(patternId, docsFile)
      else (None, fallBackDescription(patternName, patternId))

    PatternDocs(spec, description, doc)
  }

  private def fallBackDescription(patternName: String, patternId: Pattern.Id): Pattern.Description = {
    val title = Pattern.Title(patternName.replaceAll("(\\p{Upper})", " $1").trim)
    Pattern.Description(patternId, title, None, None, None)
  }

  private def parseDocsFile(patternId: Pattern.Id, file: File): (Option[String], Pattern.Description) = {
    val xml = XML.loadFile(file.toString())

    val textOpt = (xml \ "standard").headOption.map(_.text.trim)
    val codeExamples = for {
      code <- xml \ "code_comparison" \ "code"
      title <- Option((code \@ "title").trim).filter(_.nonEmpty)
    } yield {
      val sanitizedCode = code.text.trim.replaceAll("""<[//]?em>""", "")
      s"""
         |$title
         |```
         |$sanitizedCode
         |```""".stripMargin
    }

    val doc = Option((textOpt ++ codeExamples).mkString("", "\n", "\n")).filter(_.nonEmpty)
    val description =
      Pattern.Description(patternId, Pattern.Title(xml \@ "title"), textOpt.map(Pattern.DescriptionText), None, None)

    (doc, description)
  }
}
