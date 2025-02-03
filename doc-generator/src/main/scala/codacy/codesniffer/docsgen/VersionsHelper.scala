package codacy.codesniffer.docsgen

import better.files.File

object VersionsHelper {

  private[this] val properties = {
    val composerJsonString = File("composer.json").contentAsString
    val composerJson = ujson.read(composerJsonString)
    composerJson("require")
  }

  lazy val vipWordpress = properties("automattic/vipwpcs").str.replace("^","")
  lazy val cakephp = properties("cakephp/cakephp-codesniffer").str.replace("^","")
  lazy val doctrine = properties("doctrine/coding-standard").str.replace("^","")
  lazy val drupalCoder = properties("drupal/coder").str.replace("^","")
  lazy val symfony = properties("escapestudios/symfony2-coding-standard").str.replace("^","")
  lazy val magentoCS = properties("magento/magento-coding-standard").str.replace("^","")
  lazy val magentoEQP = properties("magento/marketplace-eqp").str.replace("^","")
  lazy val phpCompatibility = properties("phpcompatibility/php-compatibility").str.replace("^","")
  lazy val phpcsSecurityAudit = properties("pheromone/phpcs-security-audit").str.replace("^","")
  lazy val slevomatCS = properties("slevomat/coding-standard").str.replace("^","")
  lazy val codesniffer = properties("squizlabs/php_codesniffer").str.replace("^","")
  lazy val wordpress = properties("wp-coding-standards/wpcs").str.replace("^","")
  lazy val vaimoUK = properties("vaimo/uk-phpcs-standards").str.replace("^","")
  lazy val vaimo = properties("vaimo/phpcs-rulesets").str.replace("^","")
}
