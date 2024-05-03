## TypeHints: Property Type Hint

* Checks for missing property typehints in case they can be declared natively. If the phpDoc contains something that can be written as a native PHP 7.4+ typehint, this sniff reports that.
* Checks for useless `@var` annotations. If the native method declaration contains everything and the phpDoc does not add anything useful, it's reported as useless and can optionally be automatically removed with `phpcbf`.
* Forces to specify what's in traversable types like `array`, `iterable` and `\Traversable`.

Sniff provides the following settings:

* `enableNativeTypeHint`: enforces to transform `@var int` into native `int` typehint. It's on by default if you're on PHP 7.4+
* `enableMixedTypeHint`: enforces to transform `@var mixed` into native `mixed` typehint. It's on by default if you're on PHP 8.0+. It can be enabled only when `enableNativeTypeHint` is enabled too.
* `enableUnionTypeHint`: enforces to transform `@var string|int` into native `string|int` typehint. It's on by default if you're on PHP 8.0+. It can be enabled only when `enableNativeTypeHint` is enabled too.
* `enableIntersectionTypeHint`: enforces to transform `@var Foo&Bar` into native `Foo&Bar` typehint. It's on by default if you're on PHP 8.1+. It can be enabled only when `enableNativeTypeHint` is enabled too.
* `enableStandaloneNullTrueFalseTypeHints`: enforces to transform `@var true`, `@var false` or `@var null` into native typehints. It's on by default if you're on PHP 8.2+. It can be enabled only when `enableNativeTypeHint` is enabled too.
* `traversableTypeHints`: enforces which typehints must have specified contained type. E.g. if you set this to `\Doctrine\Common\Collections\Collection`, then `\Doctrine\Common\Collections\Collection` must always be supplied with the contained type: `\Doctrine\Common\Collections\Collection|Foo[]`.

This sniff can cause an error if you're overriding parent property which does not have typehints. In such cases add `@phpcsSuppress SlevomatCodingStandard.TypeHints.PropertyTypeHint.MissingNativeTypeHint` annotation to the property to have this sniff skip it.