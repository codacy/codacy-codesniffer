# SlevomatCodingStandard_Classes_UnusedPrivateElements

**DEPRECATED**
See https://phpstan.org/blog/detecting-unused-private-properties-methods-constants

Although PHP_CodeSniffer is not suitable for static analysis because it is limited to analysing one file at a time, it is possible to use it to perform certain checks. `UnusedPrivateElementsSniff` checks for unused methods, unused or write-only properties in a class and unused private constants. Reported unused elements are safe to remove.

This is very useful during refactoring to clean up dead code and injected dependencies.

Sniff provides the following settings:

* `alwaysUsedPropertiesAnnotations`: mark certain properties as always used, for example the ones with `@ORM\Column`
* `alwaysUsedPropertiesSuffixes`: mark properties with name ending with a certain string to be always marked as used
* `alwaysUsedMethodsAnnotations`: mark certain methods as always used, for example the ones with `@Serializer\PostDeserialize`
