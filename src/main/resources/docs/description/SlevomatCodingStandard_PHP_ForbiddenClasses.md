# SlevomatCodingStandard_PHP_ForbiddenClasses

Reports usage of forbidden classes, interfaces, parent classes and traits. And provide the following settings:

* `forbiddenClasses`: forbids creating instances with `new` keyword or accessing with `::` operator
* `forbiddenExtends`: forbids extending with `extends` keyword
* `forbiddenInterfaces`: forbids usage in `implements` section
* `forbiddenTraits`: forbids imports with `use` keyword

Optionally can be passed as an alternative for auto fixes. See `phpcs.xml` file example:

```xml
<rule ref="SlevomatCodingStandard.PHP.ForbiddenClasses">
    <properties>
        <property name="forbiddenClasses" type="array">
            <element key="Validator" value="Illuminate\Support\Facades\Validator"/>
        </property>
        <property name="forbiddenTraits" type="array">
            <element key="\AuthTrait" value="null"/>
        </property>
    </properties>
</rule>
```
