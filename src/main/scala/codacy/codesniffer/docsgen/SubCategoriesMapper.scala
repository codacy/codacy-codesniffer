package codacy.codesniffer.docsgen

import codacy.codesniffer.docsgen.parsers.PatternIdParts
import com.codacy.plugins.api.results.Pattern
import com.codacy.plugins.api.results.Pattern.Subcategory

object SubCategoriesMapper {

  private[this] val manualSubCategories: Map[String, Pattern.Subcategory.Value] =
    Map("WordPress_DB_PreparedSQL" -> Subcategory.SQLInjection,
        "WordPress_WP_PreparedSQL" -> Subcategory.SQLInjection,
        "WordPress_Security_NonceVerification" -> Subcategory.CSRF,
        "WordPress_Security_SafeRedirect" -> Subcategory.HTTP,
        "WordPress_Security_ValidatedSanitizedInput" -> Subcategory.InputValidation,
        "MEQP1_Security_InsecureFunction" -> Subcategory.InsecureModulesLibraries,
        "Magento2_Security_InsecureFunction" -> Subcategory.InsecureModulesLibraries,
        "MEQP1_Security_DiscouragedFunction" -> Subcategory.InsecureModulesLibraries)

  def subcategoryFor(patternIdParts: PatternIdParts): Option[Pattern.Subcategory.Value] =
    manualSubCategories
      .get(patternIdParts.patternId.value)
      .orElse(tryToGuessSubCategory(patternIdParts.prefix, patternIdParts.sniffType, patternIdParts.patternName))

  private[this] def tryToGuessSubCategory(patternPrefix: String,
                                          sniffType: String,
                                          patternName: String): Option[Pattern.Subcategory.Value] = {
    if (sniffType.toLowerCase == "security") {
      patternName.toLowerCase match {
        case secName if secName.contains("xss") => Some(Subcategory.XSS)
        case secName if secName.contains("http") => Some(Subcategory.HTTP)
        case _ => None
      }
    } else {
      None
    }
  }

}
