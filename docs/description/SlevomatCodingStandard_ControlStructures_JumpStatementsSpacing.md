## ControlStructures: Jump Statements Spacing

Enforces configurable number of lines around jump statements (continue, return, ...).

Sniff provides the following settings:

*   `allowSingleLineYieldStacking`: whether or not to allow multiple yield/yield from statements in a row without blank lines.
*   `linesCountBefore`: allows to configure the number of lines before jump statement.
*   `linesCountBeforeFirst`: allows to configure the number of lines before first jump statement.
*   `linesCountBeforeWhenFirstInCaseOrDefault`: allows to configure the number of lines before jump statement that is first in `case` or `default`
*   `linesCountAfter`: allows to configure the number of lines after jump statement.
*   `linesCountAfterLast`: allows to configure the number of lines after last jump statement.
*   `linesCountAfterWhenLastInCaseOrDefault`: allows to configure the number of lines after jump statement that is last in `case` or `default`
*   `linesCountAfterWhenLastInLastCaseOrDefault`: allows to configure the number of lines after jump statement that is last in last `case` or `default`
*   `jumpStatements`: allows to narrow the list of checked jump statements.

For example, with the following setting, only `continue` and `break` keywords are checked.

```xml
<rule ref="SlevomatCodingStandard.ControlStructures.JumpStatementsSpacing">
	<properties>
		<property name="jumpStatements" type="array">
			<element value="continue"/>
			<element value="break"/>
		</property>
	</properties>
</rule>
```
