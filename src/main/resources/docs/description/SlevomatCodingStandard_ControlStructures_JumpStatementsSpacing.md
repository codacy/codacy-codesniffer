# SlevomatCodingStandard_ControlStructures_JumpStatementsSpacing

Enforces configurable number of lines around jump statements (continue, return, ...).

Sniff provides the following settings:

* `allowSingleLineYieldStacking`: whether or not to allow multiple yield/yield from statements in a row without blank lines.
* `linesCountBeforeControlStructure`: allows to configure the number of lines before jump statement.
* `linesCountBeforeFirstControlStructure`: allows to configure the number of lines before first jump statement.
* `linesCountBeforeWhenFirstInCaseOrDefault`: allows to configure the number of lines before jump statement that is first in `case` or `default`
* `linesCountAfterControlStructure`: allows to configure the number of lines after jump statement.
* `linesCountAfterLastControlStructure`: allows to configure the number of lines after last jump statement.
* `linesCountAfterWhenLastInCaseOrDefault`: allows to configure the number of lines after jump statement that is last in `case` or `default`
* `linesCountAfterWhenLastInLastCaseOrDefault`: allows to configure the number of lines after jump statement that is last in last `case` or `default`
* `tokensToCheck`: allows to narrow the list of checked tokens.

For example, with the following setting, only `continue` and `break` tokens are checked.

```xml
<rule ref="SlevomatCodingStandard.ControlStructures.JumpStatementsSpacing">
	<properties>
		<property name="tokensToCheck" type="array">
			<element value="T_CONTINUE"/>
			<element value="T_BREAK"/>
		</property>
	</properties>
</rule>
```
