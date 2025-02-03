
package codacy.codesniffer.docsgen.parsers

import codacy.codesniffer.docsgen.VersionsHelper
import scala.util.matching.Regex

import better.files.File
import com.codacy.plugins.api.results.Pattern

import scala.annotation.nowarn

class VaimoParser extends DocsParser {

  override val repositoryURL = "https://bitbucket.org/vaimo/phpcs-rulesets"

  override val checkoutCommit: String = VersionsHelper.vaimo


  override val sniffRegex: Regex = """.*(Vaimo)\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

   @nowarn("msg=match may not be exhaustive")
  override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
    val sniffRegex(vaimoVersion, sniffType, patternName) = relativizedFilePath
    PatternIdParts(vaimoVersion, sniffType, patternName)
  }

override def descriptionWithDocs(rootDir: File,
                                   patternIdParts: PatternIdParts,
                                   patternFile: File
  ): (Pattern.Description, Option[String]) = {
    (description(patternIdParts, rootDir),
     this.parseExtendedDescription("Vaimo\\Sniffs\\Custom", "Vaimo", patternIdParts, rootDir)
    )
  }
    
    private[this] def description(patternIdParts: PatternIdParts, rootDir: File): Pattern.Description = {
    val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
    val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
    val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
    val title = Pattern.Title(s"$sniffName: $patternName")
    val extended = this.parseDescription("Vaimo\\Sniffs\\Custom", "Vaimo", patternIdParts, rootDir)
    Pattern.Description(patternIdParts.patternId, title, extended, None, Set.empty)
  }
}

