package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex
import scala.annotation.nowarn

class MagentoEQPDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/magento/marketplace-eqp.git"

  override val checkoutCommit: String = VersionsHelper.magentoEQP

  override val sniffRegex: Regex = """.*(MEQP1)\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  @nowarn("msg=match may not be exhaustive")
  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(magentoVersion, sniffType, patternName) = relativizedFilePath
    PatternIdParts(magentoVersion, sniffType, patternName)
  }

  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts,
                                   patternFile: File): (Pattern.Description, Option[String]) =
    (description(patternIdParts, rootDir), this.parseExtendedDescription("MEQP1\\Sniffs", patternIdParts, rootDir))

  private[this] def description(patternIdParts: PatternIdParts, rootDir: File): Pattern.Description = {
    val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
    val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
    val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
    val title = Pattern.Title(s"$sniffName: $patternName")
    val extended = this.parseDescription("MEQP1\\Sniffs", patternIdParts, rootDir)
    Pattern.Description(patternIdParts.patternId, title, extended, None, Set.empty)
  }

}
