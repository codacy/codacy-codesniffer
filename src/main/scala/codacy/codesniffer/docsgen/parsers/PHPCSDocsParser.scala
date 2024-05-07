package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex
import scala.xml.XML
import scala.annotation.nowarn

class PHPCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/squizlabs/PHP_CodeSniffer.git"

  override val checkoutCommit: String = VersionsHelper.codesniffer

  override val sniffRegex: Regex = """.*src\/Standards\/(.*?)\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  @nowarn("msg=match may not be exhaustive")
  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(standard, sniffType, patternName) = relativizedFilePath
    PatternIdParts(standard, sniffType, patternName)
  }

  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts,
                                   patternFile: File
  ): (Pattern.Description, Option[String]) = {
    val docsFile =
      rootDir / "src/Standards" / patternIdParts.prefix / "Docs" / patternIdParts.sniffType / s"${patternIdParts.patternName}Standard.xml"

    if (docsFile.exists) {
      parseDocsFile(patternIdParts.patternId, docsFile)
    } else {
      (fallBackDescription(patternIdParts), None)
    }
  }

  private[this] def fallBackDescription(patternIdParts: PatternIdParts): Pattern.Description = {
    val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
    val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
    val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
    val title = Pattern.Title(s"$sniffName: $patternName")
    Pattern.Description(patternIdParts.patternId, title, None, None, Set.empty)
  }

  private[this] def parseDocsFile(patternId: Pattern.Id, file: File): (Pattern.Description, Option[String]) = {
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
      Pattern.Description(patternId,
                          Pattern.Title(xml \@ "title"),
                          textOpt.map(Pattern.DescriptionText),
                          None,
                          Set.empty
      )

    (description, doc)
  }

}
