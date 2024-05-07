package codacy.codesniffer.docsgen

import better.files.File

object VersionsHelper {

  private[this] val properties = {
    val composerJsonString = File("composer.json").contentAsString
    val composerJson = ujson.read(composerJsonString)
    composerJson("require")
  }

  lazy val vipWordpress = properties("automattic/vipwpcs").str
  lazy val cakephp = properties("cakephp/cakephp-codesniffer").str
  lazy val doctrine = properties("doctrine/coding-standard").str
  lazy val drupalCoder = properties("drupal/coder").str
  lazy val symfony = properties("escapestudios/symfony2-coding-standard").str
  lazy val magentoCS = properties("magento/magento-coding-standard").str
  lazy val magentoEQP = properties("magento/marketplace-eqp").str
  lazy val phpCompatibility = properties("phpcompatibility/php-compatibility").str
  lazy val phpcsSecurityAudit = properties("pheromone/phpcs-security-audit").str
  lazy val slevomatCS = properties("slevomat/coding-standard").str
  lazy val codesniffer = properties("squizlabs/php_codesniffer").str
  lazy val wordpress = properties("wp-coding-standards/wpcs").str
}
