package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.CategoriesMapper
import com.codacy.plugins.api.results.{Pattern, Result}

class WordPressCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/WordPress-Coding-Standards/WordPress-Coding-Standards.git"

  private[this] val sniffRegex = """.*WordPress\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  private[this] val patternsPrefix = "WordPress"

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

  private[this] def handlePattern(rootDir: File,
                                  sourceFile: File,
                                  sniffType: String,
                                  patternName: String): PatternDocs = {
    val patternId = Pattern.Id(s"${patternsPrefix}_${sniffType}_$patternName")
    val spec = Pattern.Specification(patternId,
                                     findIssueType(sourceFile).getOrElse(Result.Level.Warn),
                                     CategoriesMapper.categoryFor(patternId, patternsPrefix, sniffType, patternName),
                                     parseParameters(sourceFile))
    val description = descriptionFor(patternName, patternId)

    PatternDocs(spec, description, None)
  }

  private[this] def descriptionFor(patternName: String, patternId: Pattern.Id): Pattern.Description = {
    val title = Pattern.Title(patternName.replaceAll("(\\p{Upper})", " $1").trim)
    Pattern.Description(patternId, title, None, None, None)
  }

}
