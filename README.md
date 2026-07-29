# Codacy PHP_CodeSniffer

This is the docker engine we use at Codacy to have [PHP_CodeSniffer](https://github.com/squizlabs/PHP_CodeSniffer) support.
You can also create a docker to integrate the tool and language of your choice!
See the [codacy-engine-scala-seed](https://github.com/codacy/codacy-engine-scala-seed) repository for more information.

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b695e76adcfa48749a8eaa33f2f2f626)](https://www.codacy.com/gh/codacy/codacy-codesniffer?utm_source=github.com&utm_medium=referral&utm_content=codacy/codacy-codesniffer&utm_campaign=Badge_Grade)
[![Build Status](https://circleci.com/gh/codacy/codacy-codesniffer.svg?style=shield)](https://circleci.com/gh/codacy/codacy-codesniffer)

## Usage

You can create the docker by doing:

```bash
docker build -t codacy-codesniffer .
```

The docker is ran with the following command:

```bash
docker run -it -v $srcDir:/src <DOCKER_NAME>:<DOCKER_VERSION>
```

## Test

We use the [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test) to test our external tools integration.
You can follow the instructions there to make sure your tool is working as expected.

## Generating the documentation

Requires `phpdoc`

```bash
wget https://github.com/phpDocumentor/phpDocumentor/releases/download/v3.4.3/phpDocumentor.phar
sudo mv phpDocumentor.phar /usr/local/bin/phpdoc
sudo chmod +x /usr/local/bin/phpdoc
```

Update the versions in `composer.json` and run

```bash
sbt "doc-generator/runMain codacy.codesniffer.docsgen.GeneratorMain"
```

This will create updated `patterns.json`, `description.json` and the individual documentation Markdown files.

## CodeSniffer configuration file

PHP CodeSniffer can be configured by adding a `phpcs.xml` file to the source code.

Currently, the tool supports this configuration file except from one feature - setting installed_paths: `<config name="installed_paths" />`

## Add new Codesniffer plugin

To add a new plugin to Codesniffer:

1.  Add the plugin dependency to `require` inside `composer.json`. This depedency must be available on [Packagist](https://packagist.org/)

2.  Add to `src/main/scala/codacy/codesniffer/docsgen/VersionsHelper.scala` the plugin version:

    ```scala
    lazy val newPlugin = properties("pluginNamespace/pluginName").str.replace("^","")
    ```

3.  Implement the plugin documentation parser inside `src/main/scala/codacy/codesniffer/docsgen/parsers`. The parser must extend `DocsParser` and override the following:

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

4.  Add the new parser to the list of parsers inside `src/main/scala/codacy/codesniffer/docsgen/Generator.scala`.

    ```scala
    private[this] val parsers: List[DocsParser] = List(new PHPCSDocsParser(), ...)
    ```

5.  [Generate the documentation](#generating-the-documentation)

## Agent Playbook: Updating This Repository End-to-End

This section is written for an AI coding agent (or a human) tasked with updating this repo — most commonly bumping the wrapped `squizlabs/php_codesniffer` version or one of its coding-standard plugins, but also base image / orb / dependency bumps. Follow it top to bottom; it tells you what to change, how to regenerate derived files, how to test locally, and how to interpret CI so you can iterate on failures without guessing.

### 1. What this repository is

This is a **Codacy engine**: a Scala wrapper (`src/main/scala/codacy/Engine.scala`, built on `codacy-engine-scala-seed` and compiled to a native binary via `sbt nativeImage`/GraalVM) that packages [PHP_CodeSniffer](https://github.com/squizlabs/PHP_CodeSniffer) — plus a bundle of PHPCS coding-standard plugins (CakePHP, Drupal Coder, Symfony2, Slevomat, PHPCompatibility, Doctrine, etc., all installed via Composer) — as a Docker image Codacy's platform can run against a customer's source code. The final Docker stage is `php:8.5-cli`, since the actual analysis at runtime is executed by PHP_CodeSniffer itself; the Scala native binary is only the Codacy-protocol wrapper.

Unlike some Codacy engines, `docs/patterns.json` and `docs/description/*` are **not committed to git** — they are listed in `.gitignore` and are generated fresh inside the Docker build's `doc-generator` stage, then copied into the final image. There is a dedicated `doc-generator` sbt subproject (`doc-generator/src/main/scala/codacy/codesniffer/docsgen/`) whose `GeneratorMain` (calling `Generator.run()`) clones/scrapes documentation for each wrapped standard via one `DocsParser` subclass per plugin (`PHPCSDocsParser`, `CakePHPDocsParser`, `DrupalCoderDocsParser`, `SlevomatCSDocsParser`, `SymfonyDocsParser`, `PHPCompatibilityDocsParser`, etc. — see `doc-generator/src/main/scala/codacy/codesniffer/docsgen/parsers/`). Each parser reads the target version from `VersionsHelper.scala`, which itself parses `composer.json`'s `require` block. This means regenerating docs needs **network access** (parsers clone/fetch upstream repos), **sbt**, and **phpdoc** (`phpDocumentor.phar`) installed locally, matching the `doc-generator` stage of the `Dockerfile`.

`docs/tests/*` and `docs/multiple-tests/*` are fixtures used by `codacy-plugins-test` to validate the engine actually produces the results it claims to for real PHP code samples. `docs/tool-description.md` is a short hand-maintained blurb about the tool.

### 2. Files that encode versions — check all of these on every update

| File | What it controls | What to check |
|---|---|---|
| `composer.json` → `require` block | The exact version of `squizlabs/php_codesniffer` and every wrapped coding-standard plugin (`cakephp/cakephp-codesniffer`, `drupal/coder`, `escapestudios/symfony2-coding-standard`, `slevomat/coding-standard`, `phpcompatibility/php-compatibility`, `doctrine/coding-standard`, etc.) | Bump the target package's version pin here. `composer.lock` must then be regenerated (`composer update <package>`) to match — see step-by-step below. |
| `doc-generator/.../VersionsHelper.scala` | Reads the same versions back out of `composer.json` for each `DocsParser` to know which upstream tag/commit to scrape docs from | Only needs a code change if you're adding a brand-new plugin (a `lazy val` per package); existing entries read `composer.json` automatically, no separate bump needed. |
| `build.sbt` → `com.codacy %% codacy-engine-scala-seed` (both `root` and `doc-generator` projects) | Codacy's engine SDK/base library | Check Maven Central for newer versions if asked to update it; keep both occurrences in sync. Also carries `scala-xml`, `scala-parallel-collections`, `ujson`, `better-files` versions used only by the doc generator. |
| `Dockerfile` → `doc-generator` base image (`sbtscala/scala-sbt:eclipse-temurin-alpine-...`) and `builder` base image (`sbtscala/scala-sbt:graalvm-ce-...`) | The sbt/Scala/JDK/GraalVM toolchain used to build the doc generator and the native image | Bump together with `build.sbt`'s `scalaVersion` if doing a Scala/sbt upgrade; check `git log -p Dockerfile` for the shape of prior bumps (see `50db415`). |
| `Dockerfile` → `phpDocumentor.phar` release URL | The `phpdoc` version used to render extended plugin docs | Bump the version in the `wget` URL when asked to update phpdoc. |
| `Dockerfile` → final-stage base image (`php:8.5-cli`) | The PHP runtime version PHP_CodeSniffer actually executes under | Bump when PHP_CodeSniffer or a plugin requires a newer PHP, or when explicitly asked — don't bump opportunistically. |
| `.circleci/config.yml` → `codacy/base` orb | Shared CircleCI steps (checkout, versioning, docker build/publish, tagging) | Check the latest published orb version (CircleCI orb registry, or `git log -p .circleci/config.yml` for the bump history as a fallback reference). |
| `.circleci/config.yml` → `codacy/plugins-test` orb | Runs `codacy-plugins-test` in CI after the image is built | Same as above. |

### 3. Step-by-step update procedure

1. **Bump the version(s)** in `composer.json`'s `require` block (and `.circleci/config.yml` orbs / `Dockerfile` base images / `build.sbt`, if applicable) as scoped by the task.
2. **Regenerate `composer.lock`** to match: `composer update <package/name> --with-dependencies` (requires PHP + Composer locally, or run inside the `php:8.5-cli` stage). Commit the updated lock file alongside `composer.json`.
3. **Regenerate the docs.** Requires `phpdoc` on `PATH` (see the `wget`/`phpDocumentor.phar` block at the top of the existing "Generating the documentation" section above), plus network access for the parsers to reach upstream plugin repositories: `sbt "doc-generator/runMain codacy.codesniffer.docsgen.GeneratorMain"`. This produces (locally, not committed) `docs/patterns.json`, `docs/description/description.json`, and the per-pattern `docs/description/*.md` files. Review the diff for new/removed/renamed patterns.
4. **Build the Docker image**: `docker build -t codacy-codesniffer .` — this exercises the same doc-generation step plus the native-image build plus the final PHP runtime assembly, so a successful build is a strong local signal.
5. **Run `codacy-plugins-test` locally** before pushing — clone https://github.com/codacy/codacy-plugins-test and run its multiple-tests / pattern / json DockerTest commands against your local image tag (CI runs `run_multiple_tests: true`, i.e. the `docs/multiple-tests/*` fixtures, via the `codacy_plugins_test/run` job).
6. **Iterate on failures**, re-running only the relevant DockerTest command after each fix.
7. **Commit** the version bump(s) together with `composer.lock` in one change (note: unlike some Codacy engines, the generated `docs/patterns.json`/`docs/description/*` are gitignored here and should NOT be committed).
8. **Push and open a PR.** CI (`.circleci/config.yml`) runs `codacy/checkout_and_version` -> `publish_docker_local` (builds and saves the image) -> `plugins_test` (`codacy_plugins_test/run` with `run_multiple_tests: true`) -> `codacy/publish_docker` (master only) -> `codacy/tag_version`.
9. **Poll the PR's real CI checks until they all pass — local validation is NOT the finish line.** After every push, run `gh pr checks <pr-url>` and keep re-polling (short sleep while any check is `pending`) until all checks finish. If a check fails, fetch its actual log (CircleCI API/UI for the failing job — don't guess), find the true root cause, fix it, push again (never `--no-verify`, never force-push), and re-poll. Repeat until every check is green. **The CI environment's toolchain can differ from your local one**, so a clean local `docker build` does not guarantee CI passes (e.g. a Composer resolution that succeeds with a stale local cache but fails fresh in CI). Only stop iterating when every check passes, or you hit a genuine product/infra decision that needs a human — in which case explain it in the PR rather than guessing.

### 4. Common failure modes and fixes

| Symptom | Likely cause | Fix |
|---|---|---|
| `composer update`/Docker build fails to resolve dependencies | New version pin conflicts with another plugin's version constraint (this repo bundles many coding standards in one `composer.json`) | Check which plugin's constraint conflicts and bump/relax it too; see `composer.lock`'s diff history for prior resolutions (e.g. commit `50db415`, which bumped `squizlabs/php_codesniffer` to v4 and dropped several no-longer-compatible parsers/plugins). |
| Doc generation step in the Docker build fails to fetch a plugin's upstream docs | The target version tag/commit referenced by that plugin's `DocsParser` (via `VersionsHelper`) doesn't exist upstream, or the upstream doc repository moved | Verify the tag exists in the plugin's real repository; update the parser's `repositoryURL`/regexes if the upstream doc structure changed (this happened for PHP_CodeSniffer itself in `50db415`, where the parser was pointed at a different upstream repo). |
| `plugins_test` / multiple-tests DockerTest fails | A sniff/rule was renamed, removed, or added upstream between versions, or plugin removal changed the pattern set | Re-run doc generation locally and diff the pattern list; update `docs/multiple-tests/*` fixtures to match verified-correct new output. |

### 5. Definition of done

- Version bump(s) reflected in `composer.json` (and `composer.lock` regenerated), plus any CI orb / Dockerfile base image bumps in scope.
- Docs regenerated locally (via `doc-generator/runMain ... GeneratorMain` or a full `docker build`) and reviewed for pattern drift, without committing the gitignored `docs/patterns.json` / `docs/description/*` output.
- Docker image builds successfully end to end (doc-generator stage, native-image builder stage, final PHP runtime stage).
- `codacy-plugins-test` commands (including multiple-tests) all pass locally against the freshly built image.
- **After pushing and opening/updating the PR, every CI check on it is green.** Poll `gh pr checks <pr-url>` and iterate on any failure (fetch the real CI log, fix, push, re-poll) until all pass — a passing local build is not sufficient, because the CI toolchain can differ from your local one (see step 9).

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
