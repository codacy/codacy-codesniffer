package codacy.codesniffer.docsgen

import better.files.File
import com.codacy.plugins.api.results.Tool
import play.api.libs.json.{Json, Writes}

object GeneratorApp extends App {

  def writeAsJsonToFile[A: Writes](a: A, filePath: String) = {
    File(filePath)
      .overwrite(Json.prettyPrint(Json.toJson(a)))
  }

  val toolName = Tool.Name("PHP Code Sniffer")
  val toolVersion = None

  val parsers = List(new PHPCSDocsParser(), new WordPressCSDocsParser())

  val patternsDocs =
    parsers
      .flatMap(_.patterns)
      .sortBy(_.pattern.patternId.value) // this makes diffs easier to read

  val toolSpecification = Tool.Specification(toolName, toolVersion, patternsDocs.map(_.pattern)(collection.breakOut))

  writeAsJsonToFile(toolSpecification, "src/main/resources/docs/patterns.json")
  writeAsJsonToFile(patternsDocs.map(_.description), "src/main/resources/docs/description/description.json")

  for {
    patternDoc <- patternsDocs
    content <- patternDoc.docs
  } {
    (File("src/main/resources/docs/description") / s"${patternDoc.pattern.patternId}.md")
      .overwrite(content)
  }

}
