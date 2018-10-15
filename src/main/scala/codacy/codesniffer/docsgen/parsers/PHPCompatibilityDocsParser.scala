package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.CategoriesMapper
import com.codacy.plugins.api.results.{Pattern, Result}

class PHPCompatibilityDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/PHPCompatibility/PHPCompatibility.git"

  private[this] val sniffRegex = """.*PHPCompatibility\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  private[this] val patternsPrefix = "PHPCompatibility"

  def handleRepo(dir: File): Set[PatternDocs] = {
    (for {
      file <- dir
        .glob(s"$sniffRegex")(File.PathMatcherSyntax.regex)
        .toList
    } yield {
      val sniffRegex(sniffType, patternName) = dir
        .relativize(file)
        .toString
      handlePattern(dir, file, sniffType, patternName)
    }).toSet
  }

  private[this] def handlePattern(rootDir: File, sourceFile: File, sniffType: String, patternName: String): PatternDocs = {
    val patternId = Pattern.Id(s"${patternsPrefix}_${sniffType}_$patternName")
    val spec = Pattern.Specification(patternId,
                                     findIssueType(sourceFile).getOrElse(Result.Level.Warn),
                                     CategoriesMapper.getCategory(patternId,
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
