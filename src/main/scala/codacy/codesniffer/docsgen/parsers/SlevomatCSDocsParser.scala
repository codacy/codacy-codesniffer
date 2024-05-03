package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex
import scala.annotation.nowarn

class SlevomatCSDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/slevomat/coding-standard.git"

  override val checkoutCommit: String = VersionsHelper.slevomatCS

  override val sniffRegex: Regex = """.*(SlevomatCodingStandard)\/Sniffs\/(.+?)\/(.+?)Sniff.php""".r

  @nowarn("msg=match may not be exhaustive")
  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(packageName, sniffType, patternName) = relativizedFilePath
    PatternIdParts(packageName, sniffType, patternName)
  }
  private object Docs {
    private var patternIdsToDocs: Map[String, String] = null

    def get(rootDir: File, patternId: String): Option[String] = {
      if (patternIdsToDocs == null) {
        val readmeFile = rootDir / "README.md"
        val pattern: Regex = "- \\[SlevomatCodingStandard[^\\]]+\\]\\(doc/[a-z\\-]+\\.md#slevomatcodingstandard[^\\)]+\\)".r
        patternIdsToDocs = readmeFile.contentAsString.linesIterator.toList.view.flatMap(line => pattern.findFirstIn(line)).flatMap { line =>
          val docFile = rootDir / line.substring(line.indexOf('(') + 1, line.indexOf('#'))
          val sectionTitle = line.substring(line.indexOf('[') + 1, line.indexOf(']'))
          val sections = docFile.contentAsString.split("####").map(_.trim).filter(_.nonEmpty)
          sections.find { section =>
            val lines = section.split("\n")
            val sectionKey = lines.head.takeWhile(c => c.isLetterOrDigit || c == '.')
            sectionKey == sectionTitle
          }.map { section =>
            val sectionKeySplitted = sectionTitle.split("\\.")
            val docTitle = "## " + sectionKeySplitted(1) + ": " + sectionKeySplitted(2).replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2") + "\n"
            (sectionTitle.replace(".", "_"),  docTitle + section.split("\n").tail.mkString("\n"))
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
