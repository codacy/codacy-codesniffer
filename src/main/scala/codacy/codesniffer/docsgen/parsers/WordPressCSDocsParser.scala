package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex

class WordPressCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/WordPress-Coding-Standards/WordPress-Coding-Standards.git"

  override val checkoutCommit: String = VersionsHelper.wordpress

  override val sniffRegex: Regex = """.*WordPress\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(sniffType, patternName) = relativizedFilePath
    PatternIdParts("WordPress", sniffType, patternName)
  }

  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts,
                                   patternFile: File): (Pattern.Description, Option[String]) = {
    val descriptionFromParts = descriptionFor(patternIdParts, rootDir)
    val description = if (isDeprecated(patternFile)) {
      val newTitle = Pattern.Title(s"${descriptionFromParts.title.value} (Deprecated)")
      descriptionFromParts.copy(title = newTitle)
    } else {
      descriptionFromParts
    }

    (description, this.parseExtendedDescription("WordPressCS\\WordPress\\Sniffs", patternIdParts, rootDir))
  }

  private[this] def isDeprecated(patternFile: File): Boolean = {
    val className = patternFile.nameWithoutExtension

    patternFile.lineIterator
      .takeWhile(line => !line.matches(s"""^.*class.*$className.*extends.*Sniff.*"""))
      .exists(_.matches("""^.*\*.*@deprecated.*"""))
  }

  private[this] def descriptionFor(patternIdParts: PatternIdParts, rootDir: File): Pattern.Description = {
    val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
    val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
    val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
    val title = Pattern.Title(s"$sniffName: $patternName")
    val extended = this.parseDescription("WordPressCS\\WordPress\\Sniffs", patternIdParts, rootDir)
    Pattern.Description(patternIdParts.patternId, title, extended, None, None)
  }

}
