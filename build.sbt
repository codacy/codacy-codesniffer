import java.io.FileInputStream
import java.util.Properties

import com.typesafe.sbt.packager.docker.Cmd

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

dockerBaseImage := "codacy-codesniffer-base"

mainClass in Compile := Some("codacy.Engine")

dockerCommands := dockerCommands.value.flatMap {
  case cmd @ Cmd("ADD", _) =>
    List(Cmd("RUN", s"adduser -u 2004 -D $dockerUser"), cmd, Cmd("RUN", "mv /opt/docker/docs /docs"))
  case other => List(other)
}
