package codacy.codesniffer.docsgen.parsers

import better.files.File
import codacy.codesniffer.docsgen.VersionsHelper
import com.codacy.plugins.api.results.Pattern

import scala.util.matching.Regex

class SymfonyDocsParser extends DocsParser {

    override val repositoryURL = "https://github.com/escapestudios/symfony2-coding-standard.git"

    override val checkoutCommit: String = VersionsHelper.symfony

    override val sniffRegex: Regex = """.*Symfony\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

    override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
        val sniffRegex(sniffType, patternName) = relativizedFilePath
        PatternIdParts("Symfony", sniffType, patternName)
    }

    override def descriptionWithDocs(rootDir: File,
                                    patternIdParts: PatternIdParts,
                                    patternFile: File): (Pattern.Description, Option[String]) = {
        (description(patternIdParts, rootDir), this.parseExtendedDescription("Symfony\\Sniffs", patternIdParts, rootDir))
    }

    private[this] def description(patternIdParts: PatternIdParts, rootDir: File): Pattern.Description = {
        val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
        val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
        val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
        val title = Pattern.Title(s"$sniffName: $patternName")
        val extended = this.parseDescription("Symfony\\Sniffs", patternIdParts, rootDir)
        Pattern.Description(patternIdParts.patternId, title, extended, None, None)
    }

}
