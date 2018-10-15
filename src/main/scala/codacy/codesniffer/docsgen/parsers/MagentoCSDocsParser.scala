package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.{CategoriesMapper, VersionsHelper}
import com.codacy.plugins.api.results.{Pattern, Result}

import scala.util.matching.Regex

class MagentoCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/magento/marketplace-eqp.git"

  override val checkoutCommit: String = VersionsHelper.magento

  override val sniffRegex: Regex = """.*(MEQP1|MEQP2)\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  def handlePatternFile(rootDir: File, patternSource: File, relativizedFilePath: String): PatternDocs = {
    val sniffRegex(magentoVersion, sniffType, patternName) = relativizedFilePath
    handlePattern(rootDir, patternSource, magentoVersion, sniffType, patternName)
  }

  private[this] def handlePattern(rootDir: File,
                            sourceFile: File,
                            magentoVersion: String,
                            sniffType: String,
                            patternName: String): PatternDocs = {
    val patternId = Pattern.Id(s"${magentoVersion}_${sniffType}_$patternName")
    val spec = Pattern.Specification(patternId,
                                     findIssueType(sourceFile),
                                     CategoriesMapper.categoryFor(patternId, magentoVersion, sniffType, patternName),
                                     parseParameters(sourceFile))

    PatternDocs(spec, description(patternName, patternId), None)
  }

  private[this] def description(patternName: String, patternId: Pattern.Id): Pattern.Description = {
    val title = Pattern.Title(patternName.replaceAll("(\\p{Upper})", " $1").trim)
    Pattern.Description(patternId, title, None, None, None)
  }

}
