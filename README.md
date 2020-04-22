# Codacy PHP_CodeSniffer

This is the docker engine we use at Codacy to have [PHP_CodeSniffer](https://github.com/squizlabs/PHP_CodeSniffer) support.
You can also create a docker to integrate the tool and language of your choice!
See the [codacy-engine-scala-seed](https://github.com/codacy/codacy-engine-scala-seed) repository for more information.

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b695e76adcfa48749a8eaa33f2f2f626)](https://www.codacy.com/gh/codacy/codacy-codesniffer?utm_source=github.com&utm_medium=referral&utm_content=codacy/codacy-codesniffer&utm_campaign=Badge_Grade)
[![Build Status](https://circleci.com/gh/codacy/codacy-codesniffer.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/codacy/codacy-codesniffer)

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

- phpdoc

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
