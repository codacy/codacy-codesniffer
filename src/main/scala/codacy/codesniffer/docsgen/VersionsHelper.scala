package codacy.codesniffer.docsgen
import java.io.FileInputStream
import java.util.Properties

object VersionsHelper {

  private[this] val properties = {
    val prop = new Properties()
    prop.load(new FileInputStream(".versions.properties"))
    prop
  }

  lazy val codesniffer = properties.getProperty("php-codesniffer")
  lazy val magento = properties.getProperty("magento-eqp")
  lazy val wordpress = properties.getProperty("wordpress")
  lazy val phpCompatibility = properties.getProperty("php-compatibility")
  lazy val `phpcs-security-audit` = properties.getProperty("phpcs-security-audit")

}
