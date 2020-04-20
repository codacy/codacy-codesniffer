# SlevomatCodingStandard_Commenting_DocCommentSpacing

Enforces configurable number of lines before first content (description or annotation), after last content (description or annotation),
between description and annotations, between two different annotations types (eg. between `@param` and `@return`).

Sniff provides the following settings:

* `linesCountBeforeFirstContent`: allows to configure the number of lines before first content (description or annotation).
* `linesCountBetweenDescriptionAndAnnotations`: allows to configure the number of lines between description and annotations.
* `linesCountBetweenDifferentAnnotationsTypes`: allows to configure the number of lines between two different annotations types.
* `linesCountBetweenAnnotationsGroups`: allows to configure the number of lines between annotations groups.
* `linesCountAfterLastContent`: allows to configure the number of lines after last content (description or annotation).
* `annotationsGroups`: allows to configurure order of annotations groups and even order of annotations in every group. Supports prefixes, eg. `@ORM\`.

```xml
<rule ref="SlevomatCodingStandard.Commenting.DocCommentSpacing">
	<properties>
		<property name="annotationsGroups" type="array">
			<element value="
				@ORM\,
			"/>
			<element value="
				@var,
				@param,
				@return,
			"/>
		</property>
	</properties>
</rule>
```

If `annotationsGroups` is set, `linesCountBetweenDifferentAnnotationsTypes` is ignored and `linesCountBetweenAnnotationsGroups` is applied.
If `annotationsGroups` is not set, `linesCountBetweenAnnotationsGroups` is ignored and `linesCountBetweenDifferentAnnotationsTypes` is applied.

Annotations not in any group are placed to automatically created last group.
