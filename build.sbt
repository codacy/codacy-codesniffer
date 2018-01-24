import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

name := """codacy-engine-codesniffer"""

version := "1.0-SNAPSHOT"

val languageVersion = "2.11.7"

scalaVersion := languageVersion

resolvers ++= Seq(
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.3.8" withSources(),
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4" withSources(),
  "com.codacy" %% "codacy-engine-scala-seed" % "2.7.1"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

// The `sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php/php.ini -i` command changes the php configuration
// to allow short open tags (without this config the tool immediately fails if a file uses short open tags)

val installAll =
  s"""apk --no-cache add bash curl git
     |&& apk --no-cache add php php-openssl php-phar php-json php-curl php-iconv php-zlib
     |&& curl -sS https://getcomposer.org/installer | php
     |&& mv composer.phar /usr/bin/composer
     |&& export COMPOSER_HOME=$$(pwd)/composer
     |&& composer global require "squizlabs/php_codesniffer=2.9.1"
     |&& ln -s $$COMPOSER_HOME/vendor/bin/phpcs /usr/bin/phpcs
     |&& git clone --branch 0.10.0 https://github.com/WordPress-Coding-Standards/WordPress-Coding-Standards.git wpcs
     |&& git clone https://github.com/magento/marketplace-eqp.git magentocs
     |&& phpcs --config-set installed_paths $$(pwd)/wpcs,$$(pwd)/magentocs
     |&& apk del curl git
     |&& rm -rf /tmp/*
     |&& rm -rf /var/cache/apk/*
     |&& sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php/php.ini -i
   """.stripMargin.replaceAll(System.lineSeparator(), " ")

mappings in Universal <++= (resourceDirectory in Compile) map { (resourceDir: File) =>
  val src = resourceDir / "docs"
  val dest = "/docs"

  for {
    path <- (src ***).get
    if !path.isDirectory
  } yield path -> path.toString.replaceFirst(src.toString, dest)
}

val dockerUser = "docker"
val dockerGroup = "docker"

daemonUser in Docker := dockerUser

daemonGroup in Docker := dockerGroup

dockerBaseImage := "develar/java"

dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("WORKDIR", _) => List(cmd,
    Cmd("RUN", installAll)
  )
  case cmd@(Cmd("ADD", "opt /opt")) => List(cmd,
    Cmd("RUN", "mv /opt/docker/docs /docs"),
    Cmd("RUN", "adduser -u 2004 -D docker"),
    ExecCmd("RUN", Seq("chown", "-R", s"$dockerUser:$dockerGroup", "/docs"): _*)
  )
  case other => List(other)
}
