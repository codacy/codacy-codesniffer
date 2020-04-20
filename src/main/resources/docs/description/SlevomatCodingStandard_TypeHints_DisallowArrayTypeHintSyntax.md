# SlevomatCodingStandard_TypeHints_DisallowArrayTypeHintSyntax

Disallows usage of array type hint syntax (eg. `int[]`, `bool[][]`) in phpDocs in favour of generic type hint syntax (eg. `array<int>`, `array<array<bool>>`).

Sniff provides the following settings:

* `traversableTypeHints`: helps fixer detect traversable type hints so `\Traversable|int[]` can be converted to `\Traversable<int>`.
