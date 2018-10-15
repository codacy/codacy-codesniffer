package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex

class MagentoCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/magento/marketplace-eqp.git"

  override val checkoutCommit: String = VersionsHelper.magento

  override val sniffRegex: Regex = """.*(MEQP1|MEQP2)\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(magentoVersion, sniffType, patternName) = relativizedFilePath
    PatternIdParts(magentoVersion, sniffType, patternName)
  }

  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts): (Pattern.Description, Option[String]) =
    (description(patternIdParts), None)

  private[this] def description(patternIdParts: PatternIdParts): Pattern.Description = {
    val title = Pattern.Title(patternIdParts.patternName.replaceAll("(\\p{Upper})", " $1").trim)
    Pattern.Description(patternIdParts.patternId, title, None, None, None)
  }

}
