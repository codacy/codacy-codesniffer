package codacy

import codacy.codesniffer.CodeSniffer
import com.codacy.tools.scala.seed.DockerEngine

object Engine extends DockerEngine(CodeSniffer)()
