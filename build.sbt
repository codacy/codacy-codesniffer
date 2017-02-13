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

val installAll =
  s"""apk update && apk upgrade
      |&& apk add bash curl git
      |&& apk add php5 php5-openssl php5-phar php5-json php5-curl
      |&& apk add php5-iconv php5-zlib
      |&& curl -sS https://getcomposer.org/installer | php
      |&& mv composer.phar /usr/bin/composer
      |&& export COMPOSER_HOME=$$(pwd)/composer
      |&& composer global require "squizlabs/php_codesniffer=2.6.2"
      |&& ln -s $$COMPOSER_HOME/vendor/bin/phpcs /usr/bin/phpcs
      |&& git clone --branch 0.10.0 https://github.com/WordPress-Coding-Standards/WordPress-Coding-Standards.git wpcs
      |&& phpcs --config-set installed_paths $$(pwd)/wpcs
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

dockerBaseImage := "frolvlad/alpine-oraclejdk8"

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
