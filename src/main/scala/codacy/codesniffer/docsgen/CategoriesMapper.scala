package codacy.codesniffer.docsgen
import com.codacy.plugins.api.results.Pattern
import com.codacy.plugins.api.results.Pattern.Category

object CategoriesMapper {

  private[this] val manualCategories: Map[String, Pattern.Category.Value] =
    Map("WordPress_DB_PreparedSQL" -> Category.Security)

  def categoryFor(patternId: Pattern.Id,
                  patternPrefix: String,
                  sniffType: String,
                  patternName: String,
                  fallback: Pattern.Category.Value = Pattern.Category.CodeStyle): Pattern.Category.Value =
    manualCategories
      .get(patternId.value)
      .orElse(tryToGuessCategory(patternPrefix, sniffType, patternName))
      .getOrElse(fallback)

  private[this] def tryToGuessCategory(patternPrefix: String,
                                       sniffType: String,
                                       patternName: String): Option[Pattern.Category.Value] = {
    Option(sniffType.toLowerCase).filter(_ == "security").map(_ => Pattern.Category.Security)
    //.orElse(another check)
  }
}
