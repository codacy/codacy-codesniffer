## Classes: Class Structure

Checks that class/trait/interface members are in the correct order.

Sniff provides the following settings:

*   `groups`: order of groups. Use multiple groups in one `<element value="">` to not differentiate among them. You can use specific groups or shortcuts.
*   `methodGroups`: custom method groups. Define a custom group for special methods based on their name, annotation, or attribute.
  *   You can use a `*   ` as prefix or suffix to filter methods name as seen in the example below.

*   *   List of supported groups*   *   :
uses,
enum cases,
public constants, protected constants, private constants,
public properties, public static properties, protected properties, protected static properties, private properties, private static properties,
constructor, static constructors, destructor, magic methods, invoke method,
public methods, protected methods, private methods,
public final methods, public static final methods, protected final methods, protected static final methods,
public abstract methods, public static abstract methods, protected abstract methods, protected static abstract methods,
public static methods, protected static methods, private static methods

*   *   List of supported shortcuts*   *   :
constants, properties, static properties, methods, all public methods, all protected methods, all private methods, static methods, final methods, abstract methods

```xml
<rule ref="SlevomatCodingStandard.Classes.ClassStructure">
	<properties>
		<property name="methodGroups" type="array">
			<element key="inject method" value="inject"/>
			<element key="inject methods" value="inject*   "/>
			<element key="phpunit before" value="setUp, @before, #PHPUnit\Framework\Attributes\Before"/>
			<element key="phpunit data provider" value="*   DataProvider"/>
		</property>

		<property name="groups" type="array">
			<element value="uses"/>

			<element value="enum cases"/>

			<!-- Public constants are first, but you don't care about the order of protected or private constants -->
			<element value="public constants"/>
			<element value="constants"/>

			<!-- You don't care about the order among the properties. The same can be done with "properties" shortcut -->
			<element value="public properties, protected properties, private properties"/>

			<!-- Constructor is first -->
			<element value="constructor"/>

			<!-- Then inject method followed by all other inject methods based on their prefix using a custom method group regardless their visibility -->
			<element value="inject method"/>
			<element value="inject methods"/>

			<!-- PHPUnit's before hooks are placed before all other public methods using a custom method group -->
			<element value="phpunit before"/>

			<!-- Then all public methods, followed by protected/private methods -->
			<element value="all public methods"/>
			<element value="methods"/>

			<!-- PHPUnit's data providers are placed after all other public methods using a custom method group -->
			<element value="phpunit data provider"/>

			<!-- Magic methods are last -->
			<element value="magic methods"/>
		</property>
	</properties>
</rule>
```
