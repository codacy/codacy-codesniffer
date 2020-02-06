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

// The `sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php/php.ini -i` command changes the php configuration
// to allow short open tags (without this config the tool immediately fails if a file uses short open tags)
val installAll =
  s"""apk --no-cache add curl git
     |&& apk --no-cache add php php-openssl php-phar php-json php-curl php-iconv php-zlib php-simplexml php-tokenizer php-xmlwriter
     |&& sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php7/php.ini -i
     |&& curl -sS https://getcomposer.org/installer | php
     |&& mv composer.phar /usr/bin/composer
     |&& export COMPOSER_HOME=$$(pwd)/composer
     |&& composer global require "squizlabs/php_codesniffer=${versionFor("php-codesniffer")}"
     |&& ln -s $$COMPOSER_HOME/vendor/bin/phpcs /usr/bin/phpcs
     |&& composer global require "slevomat/coding-standard=${versionFor("slevomat-cs")}"
     |&& composer global require "pheromone/phpcs-security-audit=${versionFor("phpcs-security-audit")}"
     |&& composer global require "magento/magento-coding-standard=${versionFor("magento-cs")}"
     |&& git clone --branch ${versionFor("wordpress")} https://github.com/WordPress-Coding-Standards/WordPress-Coding-Standards.git wpcs
     |&& git clone --branch ${versionFor("magento-eqp")} https://github.com/magento/marketplace-eqp.git magento-eqp
     |&& git clone --branch ${versionFor("php-compatibility")} https://github.com/wimg/PHPCompatibility.git phpcompatibility
     |&& phpcs --config-set installed_paths $$(pwd)/wpcs,$$(pwd)/magento-eqp,$$(pwd)/phpcompatibility
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
