package codacy.codesniffer.docsgen

import better.files.File

object VersionsHelper {

  private[this] val properties = {
    val composerJsonString = File("composer.json").contentAsString
    val composerJson = ujson.read(composerJsonString)
    val dependencies = composerJson("require")
    dependencies.obj
  }

  lazy val codesniffer = properties.getOrElse("squizlabs/php_codesniffer", ujson.Null).str
  lazy val magentoEQP = properties.getOrElse("magento/marketplace-eqp", ujson.Null).str
  lazy val magentoCS = properties.getOrElse("magento/magento-coding-standard", ujson.Null).str
  lazy val wordpress = properties.getOrElse("wp-coding-standards/wpcs", ujson.Null).str
  lazy val phpCompatibility = properties.getOrElse("phpcompatibility/php-compatibility", ujson.Null).str
  lazy val phpcsSecurityAudit = properties.getOrElse("pheromone/phpcs-security-audit", ujson.Null).str
  lazy val slevomatCS = properties.getOrElse("slevomat/coding-standard", ujson.Null).str

}
