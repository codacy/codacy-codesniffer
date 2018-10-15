package codacy.codesniffer.docsgen
import codacy.codesniffer.docsgen.parsers.PatternIdParts
import com.codacy.plugins.api.results.Pattern
import com.codacy.plugins.api.results.Pattern.Category

object CategoriesMapper {

  private[this] val manualCategories: Map[String, Pattern.Category.Value] =
    Map("WordPress_DB_PreparedSQL" -> Category.Security,
        "WordPress_WP_PreparedSQL" -> Category.Security,
        "WordPress_XSS_EscapeOutput" -> Category.Security)

  def categoryFor(patternIdParts: PatternIdParts,
                  fallback: Pattern.Category.Value = Pattern.Category.CodeStyle): Pattern.Category.Value =
    manualCategories
      .get(patternIdParts.patternId.value)
      .orElse(tryToGuessCategory(patternIdParts.prefix, patternIdParts.sniffType, patternIdParts.patternName))
      .getOrElse(fallback)

  private[this] def tryToGuessCategory(patternPrefix: String,
                                       sniffType: String,
                                       patternName: String): Option[Pattern.Category.Value] = {
    Option(sniffType.toLowerCase).filter(_ == "security").map(_ => Pattern.Category.Security)
    //.orElse(another check)
  }
}
