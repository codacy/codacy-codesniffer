import java.io.FileInputStream
import java.util.Properties

import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

name := """codacy-codesniffer"""

version := "1.0-SNAPSHOT"

val languageVersion = "2.13.1"

scalaVersion := languageVersion

libraryDependencies ++= Seq(("org.scala-lang.modules" %% "scala-xml" % "1.2.0").withSources(),
                            "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0",
                            "com.codacy" %% "codacy-engine-scala-seed" % "3.1.0")

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
     |&& git clone --branch ${versionFor("wordpress")} https://github.com/WordPress-Coding-Standards/WordPress-Coding-Standards.git wpcs
     |&& (git clone https://github.com/magento/marketplace-eqp.git magentocs
     |&&    cd magentocs && git checkout ${versionFor("magento-eqp")})
     |&& git clone --branch ${versionFor("php-compatibility")} https://github.com/wimg/PHPCompatibility.git phpcompatibility
     |&& git clone --branch ${versionFor("phpcs-security-audit")} https://github.com/FloeDesignTechnologies/phpcs-security-audit phpcs-security-audit
     |&& phpcs --config-set installed_paths $$(pwd)/wpcs,$$(pwd)/magentocs,$$(pwd)/phpcompatibility,$$(pwd)/phpcs-security-audit
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
