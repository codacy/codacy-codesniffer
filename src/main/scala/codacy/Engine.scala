package codacy

import codacy.dockerApi.DockerEngine
import codacy.codesniffer.CodeSniffer

object Engine extends DockerEngine(CodeSniffer)
