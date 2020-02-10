import java.io.FileInputStream
import java.util.Properties

import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

name := "codacy-codesniffer"

version := "1.0-SNAPSHOT"

val languageVersion = "2.13.1"

scalaVersion := languageVersion

libraryDependencies ++= Seq(("org.scala-lang.modules" %% "scala-xml" % "1.2.0").withSources(),
                            "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0",
                            "com.codacy" %% "codacy-engine-scala-seed" % "4.0.0")

enablePlugins(AshScriptPlugin)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

val versionProperties: Properties = {
  val prop = new Properties()
  prop.load(new FileInputStream(".versions.properties"))
  prop
}

def versionFor(name: String): String = {
  versionProperties.getProperty(name)
}

def installFromGit(gitUrl: String,
                   version: String,
                   composerHome: String,
                   composerNamespace: String,
                   composerName: String) = {
  s"""(
     |git clone --branch $version $gitUrl $composerHome/vendor/$composerNamespace/$composerName
     |&& cd $composerHome/vendor/$composerNamespace/$composerName
     |&& composer install --no-dev
     |)
     |""".stripMargin
}

// The `sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php/php.ini -i` command changes the php configuration
// to allow short open tags (without this config the tool immediately fails if a file uses short open tags)
val installAll =
  s"""apk --no-cache add curl git
     |&& apk --no-cache add php php-openssl php-phar php-json php-curl php-iconv php-zlib php-simplexml php-tokenizer php-xmlwriter php-mbstring php-xml php-dom
     |&& ln -s /etc/php7/php.ini /etc/php7/conf.d/php.ini
     |&& sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php7/php.ini -i
     |&& curl -sS https://getcomposer.org/installer | php
     |&& mv composer.phar /usr/bin/composer
     |&& export COMPOSER_HOME=$$(pwd)/composer
     |&& composer global require "squizlabs/php_codesniffer=${versionFor("php-codesniffer")}"
     |&& ln -s $$COMPOSER_HOME/vendor/bin/phpcs /usr/bin/phpcs
     |&& composer global require "slevomat/coding-standard=${versionFor("slevomat-cs")}"
     |&& composer global require "pheromone/phpcs-security-audit=${versionFor("phpcs-security-audit")}"
     |&& composer global require "magento/magento-coding-standard=${versionFor("magento-cs")}"
     |&& ${installFromGit("https://github.com/WordPress-Coding-Standards/WordPress-Coding-Standards.git",
                          versionFor("wordpress"),
                          s"$$COMPOSER_HOME",
                          "wordpress",
                          "wp-coding-standard")}
     |&& ${installFromGit("https://github.com/magento/marketplace-eqp.git",
                          versionFor("magento-eqp"),
                          s"$$COMPOSER_HOME",
                          "magento",
                          "marketplace-eqp")}
     |&& ${installFromGit("https://github.com/wimg/PHPCompatibility.git",
                          versionFor("php-compatibility"),
                          s"$$COMPOSER_HOME",
                          "wimg",
                          "phpcompatibility")}
     |&& phpcs --config-set installed_paths $$COMPOSER_HOME/vendor/wordpress/wp-coding-standard,$$COMPOSER_HOME/vendor/magento/marketplace-eqp,$$COMPOSER_HOME/vendor/wimg/phpcompatibility
     |&& composer global require dealerdirect/phpcodesniffer-composer-installer
     |&& apk del curl git
     |&& rm -rf /tmp/*
     |&& rm -rf /var/cache/apk/*
   """.stripMargin.replaceAll(System.lineSeparator(), " ")

mappings in Universal ++= {
  (resourceDirectory in Compile).map { (resourceDir: File) =>
    val src = resourceDir / "docs"
    val dest = "/docs"

    for {
      path <- src.allPaths.get if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
  }
}.value

val dockerUser = "docker"
val dockerGroup = "docker"

daemonUser in Docker := dockerUser

daemonGroup in Docker := dockerGroup

dockerBaseImage := "openjdk:8-jre-alpine"

mainClass in Compile := Some("codacy.Engine")

dockerCommands := dockerCommands.value.flatMap {
  case cmd @ Cmd("ADD", _) =>
    List(Cmd("RUN", s"adduser -u 2004 -D $dockerUser"),
         cmd,
         Cmd("RUN", installAll),
         Cmd("RUN", "mv /opt/docker/docs /docs"))
  case other => List(other)
}
