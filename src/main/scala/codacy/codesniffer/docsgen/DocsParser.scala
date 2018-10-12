package codacy.codesniffer.docsgen
import java.nio.file.Files

import better.files.File
import com.codacy.plugins.api.results.{Parameter, Pattern, Result}
import com.codacy.tools.scala.seed.utils.CommandRunner

case class PatternDocs(pattern: Pattern.Specification, description: Pattern.Description, docs: Option[String])

trait DocsParser {
  def repositoryURL: String

  def handleRepo(dir: File): Set[PatternDocs]

  def patterns: Set[PatternDocs] =
    withRepo(repositoryURL)(handleRepo)
      .fold(a => throw a, identity)

  private def withRepo[A](repositoryURL: String)(f: File => A): Either[Throwable, A] = {
    val dir = Files.createTempDirectory("")
    for {
      _ <- CommandRunner
        .exec(List("git", "clone", repositoryURL, dir.toString))
        .right
      res = f(dir)
      _ <- CommandRunner.exec(List("rm", "-rf", dir.toString)).right
    } yield {
      res
    }
  }

  protected val manualCategories: Map[String, Pattern.Category.Value] = Map.empty // TODO

  protected def getCategory(patternId: Pattern.Id,
                            fallback: Pattern.Category.Value = Pattern.Category.CodeStyle): Pattern.Category.Value =
    manualCategories.get(patternId.value).orElse(tryToGuessCategory(patternId)).getOrElse(fallback)

  private def tryToGuessCategory(patternId: Pattern.Id): Option[Pattern.Category.Value] = None

  protected def parseParameters(patternFile: File): Option[Set[Parameter.Specification]] = {
    val patternRegex = """.*?\spublic.*?\$(.*?)=(.*?);""".r

    Option(patternFile.lineIterator.toStream.collect {
      case patternRegex(name, defaultValue) =>
        Parameter.Specification(Parameter.Name(name.trim), Parameter.Value(defaultValue.trim))
    }).filter(_.nonEmpty)
      .map(_.toSet)
  }

  protected def findIssueType(patternFile: File): Option[Result.Level] = {
    val errorRegex = """.*->addError\(.*""".r
    val warningRegex = """.*->addWarning\(.*""".r

    patternFile.lineIterator.toStream.collectFirst {
      case errorRegex() => Result.Level.Err
      case warningRegex() => Result.Level.Warn
    }
  }
}
