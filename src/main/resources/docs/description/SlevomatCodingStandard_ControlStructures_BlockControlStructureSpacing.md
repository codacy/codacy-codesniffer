# SlevomatCodingStandard_ControlStructures_BlockControlStructureSpacing

Enforces configurable number of lines around block control structures (if, foreach, ...).

Sniff provides the following settings:

* `linesCountBefore`: allows to configure the number of lines before control structure.
* `linesCountBeforeFirst`: allows to configure the number of lines before first control structure.
* `linesCountAfter`: allows to configure the number of lines after control structure.
* `linesCountAfterLast`: allows to configure the number of lines after last control structure.
* `controlStructures`: allows to narrow the list of checked control structures.

For example, with the following setting, only `if` and `switch` keywords are checked.

```xml
<rule ref="SlevomatCodingStandard.ControlStructures.BlockControlStructureSpacing">
	<properties>
		<property name="controlStructures" type="array">
			<element value="if"/>
			<element value="switch"/>
		</property>
	</properties>
</rule>
```
