## Classes: Property Declaration

* Checks that there's a single space between a typehint and a property name: `Foo $foo`
* Checks that there's no whitespace between a nullability symbol and a typehint: `?Foo`
* Checks that there's a single space before nullability symbol or a typehint: `private ?Foo` or `private Foo`
* Checks order of modifiers

Sniff provides the following settings:

* `modifiersOrder`: allows to configure order of modifiers.
* `checkPromoted`: will check promoted properties too.
* `enableMultipleSpacesBetweenModifiersCheck`: checks multiple spaces between modifiers.