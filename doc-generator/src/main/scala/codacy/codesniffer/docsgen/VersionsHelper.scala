package codacy.codesniffer.docsgen

import better.files.File

object VersionsHelper {

  private[this] val properties = {
    val composerJsonString = File("composer.json").contentAsString
    val composerJson = ujson.read(composerJsonString)
    composerJson("require")
  }

  lazy val cakephp = properties("cakephp/cakephp-codesniffer").str.replace("^","")
  lazy val doctrine = properties("doctrine/coding-standard").str.replace("^","")
  lazy val drupalCoder = properties("drupal/coder").str.replace("^","")
  lazy val symfony = properties("escapestudios/symfony2-coding-standard").str.replace("^","")
  lazy val phpCompatibility = properties("phpcompatibility/php-compatibility").str.replace("^","")
  lazy val slevomatCS = properties("slevomat/coding-standard").str.replace("^","")
  lazy val codesniffer = properties("squizlabs/php_codesniffer").str.replace("^","")
}
