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
                                   patternIdParts: PatternIdParts): (Pattern.Description, Option[String]) = {
    (descriptionFor(patternIdParts), None)
  }

  private[this] def descriptionFor(patternIdParts: PatternIdParts): Pattern.Description = {
    val title = Pattern.Title(patternIdParts.patternName.replaceAll("(\\p{Upper})", " $1").trim)
    Pattern.Description(patternIdParts.patternId, title, None, None, None)
  }

}
