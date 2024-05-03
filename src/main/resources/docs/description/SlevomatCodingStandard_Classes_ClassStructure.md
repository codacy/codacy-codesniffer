## Classes: Class Structure

Checks that class/trait/interface members are in the correct order.

Sniff provides the following settings:

* `groups`: order of groups. Use multiple groups in one `<element value="">` to not differentiate among them. You can use specific groups or shortcuts.

**List of supported groups**:
uses,
enum cases,
public constants, protected constants, private constants,
public properties, public static properties, protected properties, protected static properties, private properties, private static properties,
constructor, static constructors, destructor, magic methods,
public methods, protected methods, private methods,
public final methods, public static final methods, protected final methods, protected static final methods,
public abstract methods, public static abstract methods, protected abstract methods, protected static abstract methods,
public static methods, protected static methods, private static methods,
private methods

**List of supported shortcuts**:
constants, properties, static properties, methods, all public methods, all protected methods, all private methods, static methods, final methods, abstract methods

```xml
<rule ref="SlevomatCodingStandard.Classes.ClassStructure">
	<properties>
		<property name="groups" type="array">
			<element value="uses"/>

			<element value="enum cases"/>

			<!-- Public constants are first, but you don't care about the order of protected or private constants -->
			<element value="public constants"/>
			<element value="constants"/>

			<!-- You don't care about the order among the properties. The same can be done with "properties" shortcut -->
			<element value="public properties, protected properties, private properties"/>

			<!-- Constructor is first, then all public methods, then protected/private methods and magic methods are last -->
			<element value="constructor"/>
			<element value="all public methods"/>
			<element value="methods"/>
			<element value="magic methods"/>
		</property>
	</properties>
</rule>
```