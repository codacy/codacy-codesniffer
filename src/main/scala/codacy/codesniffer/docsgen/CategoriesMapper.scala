package codacy.codesniffer.docsgen
import codacy.codesniffer.docsgen.parsers.PatternIdParts
import com.codacy.plugins.api.results.Pattern
import com.codacy.plugins.api.results.Pattern.{Category, Subcategory}

object CategoriesMapper {

  def categoryFor(
    patternIdParts: PatternIdParts,
    fallback: Pattern.Category.Value = Pattern.Category.CodeStyle
  ): (Pattern.Category.Value, Option[Pattern.Subcategory.Value]) = {
    patternIdParts.patternId.value match {
      case "WordPress_DB_PreparedSQL" => (Category.Security, Some(Subcategory.SQLInjection))
      case "WordPress_WP_PreparedSQL" => (Category.Security, Some(Subcategory.SQLInjection))
      case "WordPress_XSS_EscapeOutput" => (Category.Security, None)
      case "WordPress_Security_EscapeOutput" => (Category.Security, Some(Subcategory.XSS))
      case "WordPress_Security_NonceVerification" => (Category.Security, Some(Subcategory.CSRF))
      case "WordPress_Security_SafeRedirect" => (Category.Security, Some(Subcategory.HTTP))
      case "WordPress_Security_ValidatedSanitizedInput" => (Category.Security, Some(Subcategory.InputValidation))
      case "MEQP1_Security_InsecureFunction" => (Category.Security, Some(Subcategory.InsecureModulesLibraries))
      case "Magento2_Security_InsecureFunction" => (Category.Security, Some(Subcategory.InsecureModulesLibraries))
      case "MEQP1_Security_DiscouragedFunction" => (Category.Security, Some(Subcategory.InsecureModulesLibraries))

      case patternID if patternID.toLowerCase.contains("security") =>
        patternID.toLowerCase match {
          case pId if pId.contains("xss") => (Pattern.Category.Security, Some(Pattern.Subcategory.XSS))
          case pId if pId.contains("http") => (Pattern.Category.Security, Some(Pattern.Subcategory.HTTP))
          case _ => (Pattern.Category.Security, None)
        }

      case _ => (fallback, None)
    }
  }
}
