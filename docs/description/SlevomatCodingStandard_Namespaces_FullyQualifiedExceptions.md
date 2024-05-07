## Namespaces: Fully Qualified Exceptions

This sniff reduces confusion in the following code snippet:

```php
try {
	$this->foo();
} catch (Exception $e) {
	// Is this the general exception all exceptions must extend from? Or Exception from the current namespace?
}
```

All references to types named `Exception` or ending with `Exception` must be referenced via a fully qualified name:

```php
try {
	$this->foo();
} catch (\FooCurrentNamespace\Exception $e) {

} catch (\Exception $e) {

}
```

Sniff provides the following settings:

*   Exceptions with different names can be configured in `specialExceptionNames` property.
*   If your codebase uses classes that look like exceptions (because they have `Exception` or `Error` suffixes) but aren't, you can add them to `ignoredNames` property and the sniff won't enforce them to be fully qualified. Classes with `Error` suffix have to be added to ignored only if they are in the root namespace (like `LibXMLError`).
