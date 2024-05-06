name := "codacy-codesniffer"

scalaVersion := "2.13.11"

libraryDependencies ++= Seq("org.scala-lang.modules" %% "scala-xml" % "2.2.0",
                            "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
                            "com.codacy" %% "codacy-engine-scala-seed" % "6.1.2",
                            "com.lihaoyi" %% "ujson" % "3.1.2",
                            "com.github.pathikrit" %% "better-files" % "3.9.2")

enablePlugins(AshScriptPlugin)

enablePlugins(DockerPlugin)

Universal / mappings ++= {
  (Compile / resourceDirectory).map { (resourceDir: File) =>
    val src = resourceDir / "docs"
    val dest = "/docs"

    for {
      path <- src.allPaths.get if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
  }
}.value

val dockerUser = "docker"
val dockerGroup = "docker"

dockerBaseImage := "eclipse-temurin:11-jdk-alpine"

Docker / version := "1.0"

Docker / daemonUser := dockerUser

Docker / daemonGroup := dockerGroup

Compile / mainClass := Some("codacy.Engine")
