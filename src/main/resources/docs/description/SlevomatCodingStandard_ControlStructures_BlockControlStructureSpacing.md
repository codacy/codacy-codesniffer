# SlevomatCodingStandard_ControlStructures_BlockControlStructureSpacing

Enforces configurable number of lines around block control structures (if, foreach, ...).

Sniff provides the following settings:

* `linesCountBeforeControlStructure`: allows to configure the number of lines before control structure.
* `linesCountBeforeFirstControlStructure`: allows to configure the number of lines before first control structure.
* `linesCountAfterControlStructure`: allows to configure the number of lines after control structure.
* `linesCountAfterLastControlStructure`: allows to configure the number of lines after last control structure.
* `tokensToCheck`: allows to narrow the list of checked tokens.

For example, with the following setting, only `if` and `switch` tokens are checked.

```xml
<rule ref="SlevomatCodingStandard.ControlStructures.BlockControlStructureSpacing">
	<properties>
		<property name="tokensToCheck" type="array">
			<element value="T_IF"/>
			<element value="T_SWITCH"/>
		</property>
	</properties>
</rule>
```
