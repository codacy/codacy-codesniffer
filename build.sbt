ThisBuild / scalaVersion := "2.13.14"

lazy val `doc-generator` = project
  .settings(
    libraryDependencies ++= Seq("org.scala-lang.modules" %% "scala-xml" % "2.4.0",
                                "org.scala-lang.modules" %% "scala-parallel-collections" % "1.2.0",
                                "com.codacy" %% "codacy-engine-scala-seed" % "6.1.4",
                                "com.lihaoyi" %% "ujson" % "4.4.3",
                                "com.github.pathikrit" %% "better-files" % "3.9.2"
    )
  )

lazy val root = project
  .in(file("."))
  .settings(name := "codacy-codesniffer",
            libraryDependencies ++= Seq("org.scala-lang.modules" %% "scala-xml" % "2.4.0",
                                        "com.codacy" %% "codacy-engine-scala-seed" % "6.1.4"
            ),
            mainClass in Compile := Some("codacy.Engine"),
            nativeImageOptions ++= List("-O1",
                                        "-H:+ReportExceptionStackTraces",
                                        "--no-fallback",
                                        "--no-server",
                                        "--static",
                                        "-J-Xmx8G",
                                        "-J-XX:ActiveProcessorCount=8"
            )
  )
  .enablePlugins(NativeImagePlugin)
  .enablePlugins(JavaAppPackaging)