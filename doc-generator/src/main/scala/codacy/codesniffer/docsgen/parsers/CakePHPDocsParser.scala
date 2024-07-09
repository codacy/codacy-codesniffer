package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex
import scala.annotation.nowarn

class CakePHPDocsParser extends DocsParser {

  override val repositoryURL = "https://github.com/cakephp/cakephp-codesniffer.git"

  override val checkoutCommit: String = VersionsHelper.cakephp

  override val sniffRegex: Regex = new Regex(""".*(CakePHP)\/Sniffs\/(.*?)\/(.*?)Sniff.php""")

  @nowarn("msg=match may not be exhaustive")
  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(cakephpVersion, sniffType, patternName) = relativizedFilePath
    PatternIdParts(cakephpVersion, sniffType, patternName)
  }

  override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts,
                                   patternFile: File
  ): (Pattern.Description, Option[String]) = {
    (description(patternIdParts, rootDir),
     this.parseExtendedDescription("CakePHP\\Sniffs", "CakePHP", patternIdParts, rootDir)
    )
  }

  private[this] def description(patternIdParts: PatternIdParts, rootDir: File): Pattern.Description = {
    val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
    val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
    val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
    val title = Pattern.Title(s"$sniffName: $patternName")
    val extended = this.parseDescription("CakePHP\\Sniffs", "CakePHP", patternIdParts, rootDir)
    Pattern.Description(patternIdParts.patternId, title, extended, None, Set.empty)
  }

}
