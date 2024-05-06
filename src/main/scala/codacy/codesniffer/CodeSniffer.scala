package codacy.codesniffer

import java.io.File
import java.nio.file.Path

import com.codacy.plugins.api.{Options, Source}
import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import com.codacy.tools.scala.seed.utils.{CommandRunner, FileHelper}
import com.codacy.tools.scala.seed.utils.ToolHelper._
import play.api.libs.json.{JsString, JsValue}

import scala.util.{Properties, Try}
import scala.xml.{Elem, XML}

object CodeSniffer extends Tool {

  private[this] val phpVersionKey = Options.Key("php_version")

  private[this] val deprecatedPropertyRegex = ".*property is deprecated in favor of.*"

  private[this] val phpCompatibilityInstallLocation =
    "/opt/docker/app/.composer/vendor/phpcompatibility/php-compatibility"

  def apply(source: Source.Directory,
            configuration: Option[List[Pattern.Definition]],
            files: Option[Set[Source.File]],
            options: Map[Options.Key, Options.Value])(implicit specification: Tool.Specification): Try[List[Result]] = {
    Try {
      val fullConfig = configuration.withDefaultParameters
      val filesToLint: List[String] = files.fold(List(source.toString)) { paths =>
        paths.map(_.toString).toList
      }

      val version = options.get(phpVersionKey)
      val configFile = generateConfig(fullConfig, version)
      val outputFile = FileHelper.createTmpFile("", "tool-result-", ".xml")
      val command = getCommandFor(configFile, outputFile, filesToLint)

      // Possible error codes:
      // 0 - Nothing found that could be fixed.
      // 1 - Fixed all fixable errors.
      // 2 - Fixed some fixable errors, but others failed to fix.
      // others - errors
      CommandRunner.exec(command, Option(new File(source.path))) match {
        case Right(resultFromTool) if resultFromTool.exitCode <= 2 =>
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

  private[this] def parseToolResult(outputFile: Path): List[Result] = {
    val xmlResult = XML.loadFile(outputFile.toFile)
    (xmlResult \ "file").flatMap { file =>
      file.child
        .filterNot(codeMatch => codeMatch.text.matches(deprecatedPropertyRegex))
        .collect {
          case codeMatch: Elem =>
            val filePath = (file \ "@name").toString()
            val line = (codeMatch \ "@line").toString().toInt
            val message = codeMatch.text
            val rule = (codeMatch \ "@source")
              .toString()
              .split('.')
              .dropRight(1)
              .mkString("_")
              .replace("PHPCS_SecurityAudit", "Security")
            Result.Issue(Source.File(filePath), Result.Message(message), Pattern.Id(rule), Source.Line(line))
        }
    }.toList
  }

  private[this] def getCommandFor(configFile: Option[Path],
                                  outputFile: Path,
                                  filesToLint: List[String]): List[String] = {
    val configurationFile = configFile.map { config =>
      s"--standard=$config,${generateCrossCompatibilityAliasesStandard()}"
    }

    List("phpcs", "-d", "memory_limit=2048M", "--report=xml", "--encoding=utf-8", s"--report-file=$outputFile") ++ configurationFile ++ filesToLint
  }

  private[this] def generateConfig(configurationOpt: Option[List[Pattern.Definition]],
                                   phpVersion: Option[Options.Value]): Option[Path] = {
    configurationOpt.map { config =>
      val configParams = phpVersion
        .map { version =>
          s"""<config name="testVersion" value="${jsValueAsSimpleString(version: JsValue)}"/>"""
        }
        .getOrElse("")
      val rules = config.map(p => generateRule(p.patternId, p.parameters))
      val xmlConfiguration =
        s"""
        <ruleset name="Codacy">
          <description>Codacy custom standard</description>
          $configParams
          ${rules.mkString(Properties.lineSeparator)}
        </ruleset>"""

      FileHelper.createTmpFile(xmlConfiguration, prefix = "", suffix = ".xml")
    }
  }

  private[this] def generateCrossCompatibilityAliasesStandard(): Path = {
    val content =
      s"""
        |<ruleset name="PHPCS cross-version compatibility aliases">
        |  <description>Imports compatibility helper file</description>
        |  <autoload>$phpCompatibilityInstallLocation/PHPCSAliases.php</autoload>
        |</ruleset>
      """.stripMargin

    FileHelper.createTmpFile(content, prefix = "", suffix = ".xml")
  }

  private[this] def generateRule(patternIdentifier: Pattern.Id,
                                 configuredParameters: Set[Parameter.Definition]): String = {
    val params = configuredParameters
      .map { param =>
        s"""<property name="${param.name}" value="${jsValueAsSimpleString(param.value)}" />"""
      }
      .mkString(Properties.lineSeparator)

    s"""<rule ref="${convertToToolId(patternIdentifier)}"><properties>$params</properties></rule>"""
  }

  private[this] def convertToToolId(patternId: Pattern.Id): String = {
    patternId.value.replace("_", ".")
  }

  private[this] def jsValueAsSimpleString(jsValue: JsValue): String = {
    jsValue match {
      case JsString(value) => value
      case other => other.toString
    }
  }
}
