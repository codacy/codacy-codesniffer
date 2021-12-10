package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex
import scala.annotation.nowarn

class SlevomatCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/slevomat/coding-standard.git"

  override val checkoutCommit: String = VersionsHelper.slevomatCS

  override val sniffRegex: Regex = """.*(SlevomatCodingStandard)\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

  @nowarn("msg=match may not be exhaustive")
  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(magentoVersion, sniffType, patternName) = relativizedFilePath
    PatternIdParts(magentoVersion, sniffType, patternName)
  }
  private object Docs {
    private var patternIdsToDocs: Map[String, String] = null

    def get(rootDir: File, patternId: String): Option[String] = {
      if (patternIdsToDocs == null) {
        val readmeFile = rootDir / "README.md"
        val readme = readmeFile.contentAsString
        val mdList = readme.split("####").toList.tail
        patternIdsToDocs = mdList.view.flatMap { s =>
          s.linesIterator.toList match {
            case title :: text =>
              val pattern =
                title.filter(c => c.isLetterOrDigit || c == '.' || c == '_').replace('.', '_').replace('/', '_').trim
              Some(pattern -> (s"# $pattern" :: text).mkString(System.lineSeparator))
            case Nil => None
          }
        }.toMap
      }
      patternIdsToDocs.get(patternId)
    }
  }
  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts,
                                   patternFile: File): (Pattern.Description, Option[String]) = {
    val docs = Docs.get(rootDir, patternIdParts.patternId.value)
    (description(patternIdParts), docs)
  }

  private[this] def description(patternIdParts: PatternIdParts): Pattern.Description = {
    val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
    val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
    val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
    val title = Pattern.Title(s"$sniffName: $patternName")
    Pattern.Description(patternIdParts.patternId, title, None, None, Set.empty)
  }

}
