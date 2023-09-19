# Codacy PHP_CodeSniffer

This is the docker engine we use at Codacy to have [PHP_CodeSniffer](https://github.com/squizlabs/PHP_CodeSniffer) support.
You can also create a docker to integrate the tool and language of your choice!
See the [codacy-engine-scala-seed](https://github.com/codacy/codacy-engine-scala-seed) repository for more information.

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b695e76adcfa48749a8eaa33f2f2f626)](https://www.codacy.com/gh/codacy/codacy-codesniffer?utm_source=github.com&utm_medium=referral&utm_content=codacy/codacy-codesniffer&utm_campaign=Badge_Grade)
[![Build Status](https://circleci.com/gh/codacy/codacy-codesniffer.svg)](https://circleci.com/gh/codacy/codacy-codesniffer)

## Usage

You can create the docker by doing:

```bash
docker build -t codacy-codesniffer-base .
sbt docker:publishLocal
```

The docker is ran with the following command:

```bash
docker run -it -v $srcDir:/src  <DOCKER_NAME>:<DOCKER_VERSION>
```

## Test

We use the [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test) to test our external tools integration.
You can follow the instructions there to make sure your tool is working as expected.

## Generating the documentation

Requirements:

-   phpdoc

    ```bash
    curl -s http://getcomposer.org/installer | php
    php composer install
    composer global require phpdocumentor/phpdocumentor
    export PATH=$PATH:~/.composer/vendor/bin/
    ```

Update the versions in `composer.json` and run:

```bash
sbt "runMain codacy.codesniffer.docsgen.GeneratorMain"
```

This will create updated `patterns.json`, `description.json` and the individual documentation Markdown files.

## CodeSniffer configuration file

PHP CodeSniffer can be configured by adding a `phpcs.xml` file to the source code.

Currently, the tool supports this configuration file except from one feature - setting installed_paths: `<config name="installed_paths" />`

## Add new Codesniffer plugin

To add a new plugin to Codesniffer:

1.  Add the plugin dependency to `require` inside `composer.json`. This depedency must be available on [Packagist](https://packagist.org/)

2.  Add to `scala/codacy/codesniffer/docsgen/VersionsHelper.scala` the plugin version:

    ```scala
    lazy val newPlugin = properties("pluginNamespace/pluginName").str
    ```

3.  Implement the plugin documentation parser inside `scala/codacy/codesniffer/docsgen/parsers`. The parser must extend `DocsParser` and override the following:

    | Name                                                                                                                                        | Description                                                                                     |
    | ------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
    | `override val repositoryURL`                                                                                                                | Plugin's git repository                                                                         |
    | `override val checkoutCommit`                                                                                                               | Commit related to the version on plugin's git repository (you can use the version tag for that) |
    | `override val sniffRegex`                                                                                                                   | Regex to get all Sniffs implementation files                                                    |
    | `override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts`                                                               | Get the pattern id parts from pattern path                                                      |
    | `override def descriptionWithDocs(rootDir: File, patternIdParts: PatternIdParts, patternFile: File): (Pattern.Description, Option[String])` | Get tuple with small and extended description for pattern                                       |


    Example:

    ```scala
    class DrupalCoderDocsParser extends DocsParser {

        override val repositoryURL = "https://github.com/pfrenssen/coder.git"

        override val checkoutCommit: String = VersionsHelper.drupalCoder

        override val sniffRegex: Regex = """.*coder_sniffer\/(Drupal)\/Sniffs\/(.*?)\/(.*?)Sniff.php""".r

        override def patternIdPartsFor(relativizedFilePath: String): PatternIdParts = {
            val sniffRegex(drupalCoderVersion, sniffType, patternName) = relativizedFilePath
            PatternIdParts(drupalCoderVersion, sniffType, patternName)
        }

        override def descriptionWithDocs(rootDir: File,
                                        patternIdParts: PatternIdParts,
                                        patternFile: File): (Pattern.Description, Option[String]) = {
            (description(patternIdParts, rootDir), this.parseExtendedDescription("Drupal\\Sniffs", patternIdParts, rootDir))
        }

        private[this] def description(patternIdParts: PatternIdParts, rootDir: File): Pattern.Description = {
            val caseRegexPattern = """((?<=\p{Ll})\p{Lu}|\p{Lu}(?=\p{Ll}))""".r
            val patternName = caseRegexPattern.replaceAllIn(patternIdParts.patternName, " $1").trim
            val sniffName = caseRegexPattern.replaceAllIn(patternIdParts.sniffType, " $1").trim
            val title = Pattern.Title(s"$sniffName: $patternName")
            val extended = this.parseDescription("Drupal\\Sniffs", patternIdParts, rootDir)
            Pattern.Description(patternIdParts.patternId, title, extended, None, None)
        }

    }
    ```

4.  Add the new parser to the list of parsers inside `scala/codacy/codesniffer/docsgen/Generator.scala`.

    ```scala
    private[this] val parsers: List[DocsParser] = List(new PHPCSDocsParser(), ...)
    ```

5.  [Generate the documentation](#generating-the-documentation)

## What is Codacy?

[Codacy](https://www.codacy.com/) is an Automated Code Review Tool that monitors your technical debt, helps you improve your code quality, teaches best practices to your developers, and helps you save time in Code Reviews.

### Among Codacy’s features

-   Identify new Static Analysis issues
-   Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and also direct git repositories)
-   Auto-comments on Commits and Pull Requests
-   Integrations with Slack, HipChat, Jira, YouTrack
-   Track issues in Code Style, Security, Error Proneness, Performance, Unused Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
