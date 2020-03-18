package codacy.codesniffer.docsgen

import better.files.File

object VersionsHelper {

  private[this] val properties = {
    val composerJsonString = File("composer.json").contentAsString
    val composerJson = ujson.read(composerJsonString)
    val dependencies = composerJson("require")
    dependencies.obj
  }

  lazy val codesniffer = properties.get("squizlabs/php_codesniffer").head.str
  lazy val magentoEQP = properties.get("magento/marketplace-eqp").head.str
  lazy val magentoCS = properties.get("magento/magento-coding-standard").head.str
  lazy val wordpress = properties.get("wp-coding-standards/wpcs").head.str
  lazy val phpCompatibility = properties.get("phpcompatibility/php-compatibility").head.str
  lazy val phpcsSecurityAudit = properties.get("pheromone/phpcs-security-audit").head.str
  lazy val slevomatCS = properties.get("slevomat/coding-standard").head.str

}
