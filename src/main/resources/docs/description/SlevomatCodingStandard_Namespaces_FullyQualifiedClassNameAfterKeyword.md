# SlevomatCodingStandard_Namespaces_FullyQualifiedClassNameAfterKeyword

Enforces fully qualified type references after configurable set of language keywords.

For example, with the following setting, extended or implemented type must always be referenced with a fully qualified name:

```xml
<rule ref="SlevomatCodingStandard.Namespaces.FullyQualifiedClassNameAfterKeyword">
	<properties>
		<property name="keywordsToCheck" type="array">
			<element value="T_EXTENDS"/>
			<element value="T_IMPLEMENTS"/>
		</property>
	</properties>
</rule>
```
