package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex

class PHPCompatibilityDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/PHPCompatibility/PHPCompatibility.git"

  override val checkoutCommit: String = VersionsHelper.phpCompatibility

  override val sniffRegex: Regex = """.*PHPCompatibility\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  override def fallBackCategory: Pattern.Category.Value = Pattern.Category.Compatibility

  private[this] val patternsPrefix = "PHPCompatibility"

  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(sniffType, patternName) = relativizedFilePath
    PatternIdParts(patternsPrefix, sniffType, patternName)
  }

  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts): (Pattern.Description, Option[String]) =
    (description(patternIdParts), None)

  private[this] def description(patternIdParts: PatternIdParts): Pattern.Description = {
    val title = Pattern.Title(patternIdParts.patternName.replaceAll("(\\p{Upper})", " $1").trim)
    Pattern.Description(patternIdParts.patternId, title, None, None, None)
  }

}
