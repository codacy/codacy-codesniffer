package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex

class PHPCSSecurityAuditDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/FloeDesignTechnologies/phpcs-security-audit.git"

  override val checkoutCommit: String = VersionsHelper.phpcsSecurityAudit

  override def fallBackCategory: Pattern.Category.Value = Pattern.Category.Security

  override val sniffRegex: Regex = """.*Security\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(sniffType, patternName) = relativizedFilePath
    PatternIdParts("Security", sniffType, patternName)
  }
  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts,
                                   patternFile: File): (Pattern.Description, Option[String]) = {
    (descriptionFor(patternIdParts, rootDir),
     this.parseExtendedDescription("PHPCS_SecurityAudit\\Security\\Sniffs", patternIdParts, rootDir))
  }

  private[this] def descriptionFor(patternIdParts: PatternIdParts, rootDir: File): Pattern.Description = {
    val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
    val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
    val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
    val title = Pattern.Title(s"${patternIdParts.prefix} $sniffName related issue: $patternName")
    val extended = this.parseDescription("PHPCS_SecurityAudit\\Security\\Sniffs", patternIdParts, rootDir)
    Pattern.Description(patternIdParts.patternId, title, extended, None, Set.empty)
  }

}
