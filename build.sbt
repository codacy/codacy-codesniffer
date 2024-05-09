name := "codacy-codesniffer"

scalaVersion := "2.13.13"

libraryDependencies ++= Seq("org.scala-lang.modules" %% "scala-xml" % "2.2.0",
                            "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
                            "com.codacy" %% "codacy-engine-scala-seed" % "6.1.2",
                            "com.lihaoyi" %% "ujson" % "3.1.2",
                            "com.github.pathikrit" %% "better-files" % "3.9.2")

enablePlugins(AshScriptPlugin)

Compile / mainClass := Some("codacy.Engine")
