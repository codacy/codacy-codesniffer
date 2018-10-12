package codacy.codesniffer.docsgen

import better.files.File
import com.codacy.plugins.api.results.{Pattern, Result}

class WordPressCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/WordPress-Coding-Standards/WordPress-Coding-Standards.gits"

  private val sniffRegex = ".*WordPress/Sniffs/(.*?)/(.*?)Sniff.php".r

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

  private def handlePattern(rootDir: File, sourceFile: File, sniffType: String, patternName: String): PatternDocs = {
    val patternId = Pattern.Id(s"WordPress_${sniffType}_$patternName")
    val spec = Pattern.Specification(patternId,
                                     findIssueType(sourceFile).getOrElse(Result.Level.Warn),
                                     getCategory(patternId),
                                     parseParameters(sourceFile))
    val description = getDescription(patternName, patternId)

    PatternDocs(spec, description, None)
  }

  private def getDescription(patternName: String, patternId: Pattern.Id): Pattern.Description = {
    val title = Pattern.Title(patternName.replaceAll("(\\p{Upper})", " $1").trim)
    Pattern.Description(patternId, title, None, None, None)
  }

}
