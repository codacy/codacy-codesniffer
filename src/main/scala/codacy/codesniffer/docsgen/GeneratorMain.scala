package codacy.codesniffer.docsgen

object GeneratorMain {

  def main(args: Array[String]): Unit = {
    new Generator().run()
  }
}
