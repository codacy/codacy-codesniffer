# SlevomatCodingStandard_ControlStructures_UselessTernaryOperator

Reports useless ternary operator where both branches return `true` or `false`.

Sniff provides the following settings:

* `assumeAllConditionExpressionsAreAlreadyBoolean` (defaults to `false`).

## Installation

The recommended way to install Slevomat Coding Standard is [through Composer](http://getcomposer.org).

```JSON
{
	"require-dev": {
		"slevomat/coding-standard": "~6.0"
	}
}
```

It's also recommended to install [jakub-onderka/php-parallel-lint](https://github.com/JakubOnderka/PHP-Parallel-Lint) which checks source code for syntax errors. Sniffs count on the processed code to be syntactically valid (no parse errors), otherwise they can behave unexpectedly. It is advised to run `PHP-Parallel-Lint` in your build tool before running `PHP_CodeSniffer` and exiting the build process early if `PHP-Parallel-Lint` fails.

## How to run the sniffs

You can choose one of two ways to run only selected sniffs from the standard on your codebase:

### Choose which sniffs to run

The recommended way is to write your own ruleset.xml by referencing only the selected sniffs. This is a sample ruleset.xml:

```xml
<?xml version="1.0"?>
<ruleset name="AcmeProject">
	<config name="installed_paths" value="../../slevomat/coding-standard"/><!-- relative path from PHPCS source location -->
	<rule ref="SlevomatCodingStandard.Arrays.TrailingArrayComma"/>
	<!-- other sniffs to include -->
</ruleset>
```

Then run the `phpcs` executable the usual way:

```
vendor/bin/phpcs --standard=ruleset.xml --extensions=php --tab-width=4 -sp src tests
```

### Exclude sniffs you don't want to run

You can also mention Slevomat Coding Standard in your project's `ruleset.xml` and exclude only some sniffs:

```xml
<?xml version="1.0"?>
<ruleset name="AcmeProject">
	<rule ref="vendor/slevomat/coding-standard/SlevomatCodingStandard/ruleset.xml"><!-- relative path to your ruleset.xml -->
		<!-- sniffs to exclude -->
	</rule>
</ruleset>
```

However it is not a recommended way to use Slevomat Coding Standard, because your build can break when moving between minor versions of the standard (which can happen if you use `^` or `~` version constraint in `composer.json`). We regularly add new sniffs even in minor versions meaning your code won't most likely comply with new minor versions of the package.

## Fixing errors automatically

Sniffs in this standard marked by the 🔧 symbol support [automatic fixing of coding standard violations](https://github.com/squizlabs/PHP_CodeSniffer/wiki/Fixing-Errors-Automatically). To fix your code automatically, run phpcbf instead of phpcs:

```
vendor/bin/phpcbf --standard=ruleset.xml --extensions=php --tab-width=4 -sp src tests
```

Always remember to back up your code before performing automatic fixes and check the results with your own eyes as the automatic fixer can sometimes produce unwanted results.

## Suppressing sniffs locally

Selected sniffs in this standard marked by the 🚧 symbol can be suppressed for a specific piece of code using an annotation. Consider the following example:

```php
/**
 * @param int $max
 */
public function createProgressBar($max = 0): ProgressBar
{

}
```

The parameter `$max` could have a native `int` scalar typehint. But because the method in the parent class does not have this typehint, so this one cannot have it either. PHP_CodeSniffer shows a following error:

```
----------------------------------------------------------------------
FOUND 1 ERROR AFFECTING 1 LINE
----------------------------------------------------------------------
 67 | ERROR | [x] Method ErrorsConsoleStyle::createProgressBar()
    |       |     does not have native type hint for its parameter $max
    |       |     but it should be possible to add it based on @param
    |       |     annotation "int".
    |       |     (SlevomatCodingStandard.TypeHints.ParameterTypeHint.MissingNativeTypeHint)
```

If we want to suppress this error instead of fixing it, we can take the error code (`SlevomatCodingStandard.TypeHints.ParameterTypeHint.MissingNativeTypeHint`) and use it with a `@phpcsSuppress` annotation like this:

```php
/**
 * @phpcsSuppress SlevomatCodingStandard.TypeHints.ParameterTypeHint.MissingNativeTypeHint
 * @param int $max
 */
public function createProgressBar($max = 0): ProgressBar
{

}
```

## Contributing

To make this repository work on your machine, clone it and run these two commands in the root directory of the repository:

```
composer install
bin/phing
```

After writing some code and editing or adding unit tests, run phing again to check that everything is OK:

```
bin/phing
```

We are always looking forward for your bugreports, feature requests and pull requests. Thank you.

## Code of Conduct

This project adheres to a [Contributor Code of Conduct](https://github.com/slevomat/coding-standard/blob/master/CODE_OF_CONDUCT.md). By participating in this project and its community, you are expected to uphold this code.