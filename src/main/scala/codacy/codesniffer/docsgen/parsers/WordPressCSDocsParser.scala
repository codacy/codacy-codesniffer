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
                                   patternIdParts: PatternIdParts, patternFile: File): (Pattern.Description, Option[String]) = {
    val descriptionFromParts = descriptionFor(patternIdParts)
    val description = if (isDeprecated(patternFile)) {
      val newTitle = Pattern.Title(s"${descriptionFromParts.title.value} (Deprecated)")
      descriptionFromParts.copy(title = newTitle)
    } else {
      descriptionFromParts
    }
    (description, None)
  }

  private[this] def isDeprecated(patternFile: File): Boolean = {
    val className = patternFile.nameWithoutExtension

    patternFile.lineIterator
      .takeWhile(line => !line.matches(s"""^.*class.*$className.*extends.*Sniff.*"""))
      .exists(_.matches("""^.*\*.*@deprecated.*"""))
  }

  private[this] def descriptionFor(patternIdParts: PatternIdParts): Pattern.Description = {
    val title = Pattern.Title(patternIdParts.patternName.replaceAll("(\\p{Upper})", " $1").trim)
    Pattern.Description(patternIdParts.patternId, title, None, None, None)
  }

}
