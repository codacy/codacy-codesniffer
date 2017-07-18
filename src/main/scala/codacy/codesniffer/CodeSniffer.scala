package codacy.codesniffer

import java.nio.file.Path

import codacy.dockerApi._
import codacy.dockerApi.utils.{CommandRunner, FileHelper, ToolHelper}
import play.api.libs.json.{JsString, JsValue}

import scala.util.{Properties, Try}
import scala.xml.{Elem, XML}

object CodeSniffer extends Tool {

  override def apply(path: Path, conf: Option[List[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[List[Result]] = {
    Try {
      val fullConfig = ToolHelper.getPatternsToLint(conf)
      val filesToLint: List[String] = files.fold(List(path.toString)) {
        paths =>
          paths.map(_.toString).toList
      }
      val configFile = generateConfig(fullConfig)
      val outputFile = FileHelper.createTmpFile("", "tool-result-", ".xml")
      val command = getCommandFor(configFile, outputFile, filesToLint)

      CommandRunner.exec(command, Some(path.toFile)) match {
        case Right(resultFromTool) if resultFromTool.exitCode < 2 =>
          parseToolResult(outputFile)
        case Right(resultFromTool) =>
          val msg =
            s"""
               |CodeSniffer exited with code ${resultFromTool.exitCode}
               |stdout: ${resultFromTool.stdout.mkString(Properties.lineSeparator)}
               |stderr: ${resultFromTool.stderr.mkString(Properties.lineSeparator)}
                """.stripMargin
          throw new Exception(msg)
        case Left(failure) =>
          throw failure
      }
    }
  }

  private def parseToolResult(outputFile: Path)(implicit spec: Spec): List[Result] = {
    val xmlResult = XML.loadFile(outputFile.toFile)
    (xmlResult \ "file").flatMap {
      file =>
        file.child.collect {
          case codeMatch: Elem =>
            val filePath = (file \ "@name").toString()
            val line = (codeMatch \ "@line").toString().toInt
            val message = codeMatch.text
            val rule = (codeMatch \ "@source").toString()
              .split('.')
              .dropRight(1)
              .mkString("_")
            Issue(
              SourcePath(filePath),
              ResultMessage(message),
              PatternId(rule),
              ResultLine(line)
            )
        }
    }.toList
  }

  private def getCommandFor(configFile: Option[Path], outputFile: Path, filesToLint: List[String]): List[String] = {
    val configurationFile = configFile.map {
      config =>
        "--standard=" + config.toString
    }

    List("phpcs", "-d", "memory_limit=-1", "--report=xml", s"--report-file=$outputFile") ++ configurationFile ++ filesToLint
  }

  private def generateConfig(configurationOpt: Option[List[PatternDef]]): Option[Path] = {
    configurationOpt.map { config =>

      val rules = config.map(p => generateRule(p.patternId, p.parameters))
      val xmlConfiguration =
        """
        <ruleset name="Codacy"><description>Codacy custom standard</description>
        """ + rules.mkString(Properties.lineSeparator) + "</ruleset>"

      FileHelper.createTmpFile(xmlConfiguration, prefix = "", suffix = ".xml")
    }
  }

  private def generateRule(patternIdentifier: PatternId, configuredParameters: Option[Set[ParameterDef]]): String = {
    val parameters = configuredParameters.getOrElse(Set.empty[ParameterDef])

    val params = parameters.map { param =>
      s"""<property name="${param.name}" value="${jsValueAsSimpleString(param.value)}" />"""
    }.mkString(Properties.lineSeparator)

    s"""<rule ref="${convertToToolId(patternIdentifier)}"><properties>$params</properties> </rule>"""
  }

  private def convertToToolId(patternId: PatternId): String = {
    patternId.value.replace("_", ".")
  }

  private def jsValueAsSimpleString(jsValue: JsValue): String = {
    jsValue match {
      case JsString(value) => value
      case other => other.toString
    }
  }
}
