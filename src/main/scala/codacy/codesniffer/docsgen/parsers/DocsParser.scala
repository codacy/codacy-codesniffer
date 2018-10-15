package codacy.codesniffer.docsgen.parsers

import java.nio.file.Files

import better.files.File
import com.codacy.plugins.api.results.{Parameter, Pattern, Result}
import com.codacy.tools.scala.seed.utils.CommandRunner

import scala.util.matching.Regex

case class PatternDocs(pattern: Pattern.Specification, description: Pattern.Description, docs: Option[String])

trait DocsParser {
  def repositoryURL: String

  def checkoutCommit: String

  def sniffRegex: Regex

  def handlePatternFile(rootDir: File, patternSource: File, relativizedFilePath: String): PatternDocs

  def patterns: Set[PatternDocs] =
    withRepo(repositoryURL, checkoutCommit)(handleRepo)
      .fold(a => throw a, identity)

  def handleRepo(dir: File): Set[PatternDocs] = {
    (for {
      file <- dir
        .glob(sniffRegex.toString())(File.PathMatcherSyntax.regex)
        .toList
      relativizedFilePath = dir.relativize(file).toString
    } yield handlePatternFile(dir, file, relativizedFilePath))(collection.breakOut)
  }

  private[this] def withRepo[A](repositoryURL: String, checkoutCommit: String)(f: File => A): Either[Throwable, A] = {
    val dir = Files.createTempDirectory("")
    for {
      _ <- CommandRunner
        .exec(List("git", "clone", repositoryURL, dir.toString))
        .right
      _ <- CommandRunner.exec(List("git", "checkout", checkoutCommit), Some(dir.toFile))
      res = f(dir)
      _ <- CommandRunner.exec(List("rm", "-rf", dir.toString)).right
    } yield {
      res
    }
  }

  protected def parseParameters(patternFile: File): Option[Set[Parameter.Specification]] = {
    val patternRegex = """.*?\spublic.*?\$(.*?)=(.*?);""".r

    Option(patternFile.lineIterator.toStream.collect {
      case patternRegex(name, defaultValue) =>
        Parameter.Specification(Parameter.Name(name.trim), Parameter.Value(defaultValue.trim))
    }).filter(_.nonEmpty)
      .map(_.toSet)
  }

  protected def findIssueType(patternFile: File, fallback: Result.Level = Result.Level.Warn): Result.Level = {
    val errorRegex = """.*->addError\(.*""".r
    val warningRegex = """.*->addWarning\(.*""".r

    patternFile.lineIterator.toStream
      .collectFirst {
        case errorRegex() => Result.Level.Err
        case warningRegex() => Result.Level.Warn
      }
      .getOrElse(fallback)
  }
}
