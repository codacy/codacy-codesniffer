package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.{CategoriesMapper, VersionsHelper}
import com.codacy.plugins.api.results.{Pattern, Result}

class PHPCompatibilityDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/PHPCompatibility/PHPCompatibility.git"

  override val checkoutCommit: String = VersionsHelper.phpCompatibility

  override val sniffRegex = """.*PHPCompatibility\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  private[this] val patternsPrefix = "PHPCompatibility"

  def handlePatternFile(rootDir: File, patternSource: File, relativizedFilePath: String): PatternDocs = {
    val sniffRegex(sniffType, patternName) = relativizedFilePath
    handlePattern(rootDir, patternSource, sniffType, patternName)
  }

  private[this] def handlePattern(rootDir: File,
                                  sourceFile: File,
                                  sniffType: String,
                                  patternName: String): PatternDocs = {
    val patternId = Pattern.Id(s"${patternsPrefix}_${sniffType}_$patternName")
    val spec = Pattern.Specification(patternId,
                                     findIssueType(sourceFile),
                                     CategoriesMapper.categoryFor(patternId,
                                                                  patternsPrefix,
                                                                  sniffType,
                                                                  patternName,
                                                                  fallback = Pattern.Category.Compatibility),
                                     parseParameters(sourceFile))

    PatternDocs(spec, description(patternName, patternId), None)
  }

  private[this] def description(patternName: String, patternId: Pattern.Id): Pattern.Description = {
    val title = Pattern.Title(patternName.replaceAll("(\\p{Upper})", " $1").trim)
    Pattern.Description(patternId, title, None, None, None)
  }

}
