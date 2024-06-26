## TypeHints: Return Type Hint

*   Checks for missing return typehints in case they can be declared natively. If the phpDoc contains something that can be written as a native PHP 7.0+ typehint, this sniff reports that.
*   Checks for useless `@return` annotations. If the native method declaration contains everything and the phpDoc does not add anything useful, it's reported as useless and can optionally be automatically removed with `phpcbf`.
*   Forces to specify what's in traversable types like `array`, `iterable` and `\Traversable`.

Sniff provides the following settings:

*   `enableObjectTypeHint`: enforces to transform `@return object` into native `object` typehint. It's on by default if you're on PHP 7.2+
*   `enableStaticTypeHint`: enforces to transform `@return static` into native `static` typehint. It's on by default if you're on PHP 8.0+
*   `enableMixedTypeHint`: enforces to transform `@return mixed` into native `mixed` typehint. It's on by default if you're on PHP 8.0+
*   `enableUnionTypeHint`: enforces to transform `@return string|int` into native `string|int` typehint. It's on by default if you're on PHP 8.0+.
*   `enableIntersectionTypeHint`: enforces to transform `@return Foo&Bar` into native `Foo&Bar` typehint. It's on by default if you're on PHP 8.1+.
*   `enableNeverTypeHint`: enforces to transform `@return never` into native `never` typehint. It's on by default if you're on PHP 8.1+.
*   `enableStandaloneNullTrueFalseTypeHints`: enforces to transform `@return true`, `@return false` or `@return null` into native typehints. It's on by default if you're on PHP 8.2+.
*   `traversableTypeHints`: enforces which typehints must have specified contained type. E.g. if you set this to `\Doctrine\Common\Collections\Collection`, then `\Doctrine\Common\Collections\Collection` must always be supplied with the contained type: `\Doctrine\Common\Collections\Collection|Foo[]`.

This sniff can cause an error if you're overriding or implementing a parent method which does not have typehints. In such cases add `@phpcsSuppress SlevomatCodingStandard.TypeHints.ReturnTypeHint.MissingNativeTypeHint` annotation to the method to have this sniff skip it.
