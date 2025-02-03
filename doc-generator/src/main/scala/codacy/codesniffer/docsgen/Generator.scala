package codacy.codesniffer.docsgen

import better.files.File
import codacy.codesniffer.docsgen.parsers._
import com.codacy.plugins.api.results.Tool
import play.api.libs.json.{Json, Writes}

import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.ForkJoinTaskSupport
import scala.collection.parallel.immutable.ParSeq

class Generator() {

  private[this] val toolName = Tool.Name("phpcs")
  private[this] val toolVersion = Option(Tool.Version(VersionsHelper.codesniffer))

  val docsDir = File("./docs")
  val patternsFile: File = docsDir / "patterns.json"
  val descriptionsDir: File = docsDir / "description"
  val descriptionFile: File = descriptionsDir / "description.json"

  private[this] val parsers: List[DocsParser] =
    List(new CakePHPDocsParser(),
         new DrupalCoderDocsParser(),
         new MagentoCSDocsParser(),
         new MagentoEQPDocsParser(),
         new PHPCompatibilityDocsParser(),
         new PHPCSDocsParser(),
         new PHPCSSecurityAuditDocsParser(),
         new SlevomatCSDocsParser(),
         new SymfonyDocsParser(),
         new VaimoParser(),
         new VaimoUKParser(),
         new VipWordPressDocsParser(),
         new WordPressCSDocsParser()
    )

  def run(): Unit = {
    docsDir.createDirectories()
    descriptionsDir.createDirectories()

    writeToFiles(toolName,
                 toolVersion,
                 parallelParsers
                   .flatMap(_.patterns)
                   .seq
    )
  }

  private[this] def parallelParsers: ParSeq[DocsParser] = {
    val parallel = parsers.par
    // for parallel cloning
    parallel.tasksupport = new ForkJoinTaskSupport(new java.util.concurrent.ForkJoinPool(parsers.length))
    parallel
  }

  private[this] def fullDescription(patternDocs: PatternDocs) = {
    // fallbacks: full description -> pattern description -> pattern title
    patternDocs.docs.getOrElse(
      patternDocs.description.description.map(_.value).getOrElse(patternDocs.description.title.value + "\n")
    )
  }

  private[this] def writeToFiles(toolName: Tool.Name,
                                 toolVersion: Option[Tool.Version],
                                 docs: Seq[PatternDocs]
  ): Unit = {
    val sortedDocs = docs.sortBy(_.pattern.patternId.value)

    val toolSpecification = Tool.Specification(toolName, toolVersion, sortedDocs.view.map(_.pattern).to(Set))

    writeAsJsonToFile(toolSpecification, patternsFile)
    writeAsJsonToFile(sortedDocs.map(_.description), descriptionFile)

    for {
      patternDoc <- sortedDocs
      content = fullDescription(patternDoc)
    } {
      (descriptionsDir / s"${patternDoc.pattern.patternId}.md")
        .overwrite(content)
    }
  }

  private[this] def writeAsJsonToFile[A: Writes](a: A, file: File): File = {
    file.overwrite(Json.prettyPrint(Json.toJson(a)))
  }
}
