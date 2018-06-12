package codacy.codesniffer

import java.io.File
import java.nio.file.Path

import codacy.docker.api._
import codacy.docker.api.utils.ToolHelper
import codacy.dockerApi.utils.{CommandRunner, FileHelper}
import play.api.libs.json.{JsString, JsValue}

import scala.util.{Properties, Try}
import scala.xml.{Elem, XML}

object CodeSniffer extends Tool {

  def apply(source: Source.Directory, configuration: Option[List[Pattern.Definition]], files: Option[Set[Source.File]],
            options: Map[Configuration.Key, Configuration.Value])
           (implicit specification: Tool.Specification): Try[List[Result]] = {
    Try {
      val fullConfig = ToolHelper.patternsToLint(configuration)
      val filesToLint: List[String] = files.fold(List(source.toString)) {
        paths => paths.map(_.toString).toList
      }
      val configFile = generateConfig(fullConfig)
      val outputFile = FileHelper.createTmpFile("", "tool-result-", ".xml")
      val command = getCommandFor(configFile, outputFile, filesToLint)

      CommandRunner.exec(command, Option(new File(source.path))) match {
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

  private def parseToolResult(outputFile: Path): List[Result] = {
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
            Result.Issue(
              Source.File(filePath),
              Result.Message(message),
              Pattern.Id(rule),
              Source.Line(line)
            )
        }
    }.toList
  }

  private def getCommandFor(configFile: Option[Path], outputFile: Path, filesToLint: List[String]): List[String] = {
    val configurationFile = configFile.map {
      config => "--standard=" + config.toString
    }

    List("phpcs", "-d", "memory_limit=-1", "--report=xml", s"--report-file=$outputFile") ++ configurationFile ++ filesToLint
  }

  private def generateConfig(configurationOpt: Option[List[Pattern.Definition]]): Option[Path] = {
    configurationOpt.map { config =>

      val rules = config.map(p => generateRule(p.patternId, p.parameters))
      val xmlConfiguration =
        """
        <ruleset name="Codacy"><description>Codacy custom standard</description>
        """ + rules.mkString(Properties.lineSeparator) + "</ruleset>"

      FileHelper.createTmpFile(xmlConfiguration, prefix = "", suffix = ".xml")
    }
  }

  private def generateRule(patternIdentifier: Pattern.Id, configuredParameters: Option[Set[Parameter.Definition]]): String = {
    val parameters = configuredParameters.getOrElse(Set.empty[Parameter.Definition])

    val params = parameters.map { param =>
      s"""<property name="${param.name}" value="${jsValueAsSimpleString(param.value)}" />"""
    }.mkString(Properties.lineSeparator)

    s"""<rule ref="${convertToToolId(patternIdentifier)}"><properties>$params</properties><"""
  }

  private def convertToToolId(patternId: Pattern.Id): String = {
    patternId.value.replace("_", ".")
  }

  private def jsValueAsSimpleString(jsValue: JsValue): String = {
    jsValue match {
      case JsString(value) => value
      case other => other.toString
    }
  }
}
