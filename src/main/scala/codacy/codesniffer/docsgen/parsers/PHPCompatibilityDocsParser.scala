package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex
import scala.annotation.nowarn

class PHPCompatibilityDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/PHPCompatibility/PHPCompatibility.git"

  override val checkoutCommit: String = VersionsHelper.phpCompatibility

  override val sniffRegex: Regex = """.*PHPCompatibility\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  override def fallBackCategory: Pattern.Category.Value = Pattern.Category.Compatibility

  private[this] val patternsPrefix = "PHPCompatibility"

  @nowarn("msg=match may not be exhaustive")
  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(sniffType, patternName) = relativizedFilePath
    PatternIdParts(patternsPrefix, sniffType, patternName)
  }

  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts,
                                   patternFile: File): (Pattern.Description, Option[String]) =
    (description(patternIdParts, rootDir),
     this.parseExtendedDescription("PHPCompatibility\\Sniffs", "PHPCompatibility", patternIdParts, rootDir))

  private[this] def description(patternIdParts: PatternIdParts, rootDir: File): Pattern.Description = {
    val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
    val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
    val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
    val title = Pattern.Title(s"PHP Compatibility related issue ($sniffName): $patternName")
    val extended = this.parseDescription("PHPCompatibility\\Sniffs", "PHPCompatibility", patternIdParts, rootDir)
    Pattern.Description(patternIdParts.patternId, title, extended, None, Set.empty)
  }

}
