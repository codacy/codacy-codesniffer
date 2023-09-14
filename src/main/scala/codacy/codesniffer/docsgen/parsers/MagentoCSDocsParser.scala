package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex
import scala.annotation.nowarn

class MagentoCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/magento/magento-coding-standard.git"

  override val checkoutCommit: String = VersionsHelper.magentoCS

  override val sniffRegex: Regex = """.*(Magento2)\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  @nowarn("msg=match may not be exhaustive")
  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(magentoVersion, sniffType, patternName) = relativizedFilePath
    PatternIdParts(magentoVersion, sniffType, patternName)
  }

  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts,
                                   patternFile: File): (Pattern.Description, Option[String]) =
    (description(patternIdParts, rootDir: File),
     this.parseExtendedDescription("Magento2\\Sniffs", patternIdParts, rootDir))

  private[this] def description(patternIdParts: PatternIdParts, rootDir: File): Pattern.Description = {
    val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
    val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
    val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
    val title = Pattern.Title(s"$sniffName: $patternName")
    val description = this.parseDescription("Magento2\\Sniffs", patternIdParts, rootDir)
    Pattern.Description(patternIdParts.patternId, title, description, None, Set.empty)
  }
}
