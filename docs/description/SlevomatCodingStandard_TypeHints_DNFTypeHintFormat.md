## TypeHints: DNFType Hint Format

Checks format of DNF type hints. The same checks can also be applied to type hints inside `@param`, `@return`, `@var`, `@property` and `@property-read` annotations by enabling `enableForDocComments`.

Sniff provides the following settings:

*   `enable`: either to enable or not this sniff. By default, it is enabled for PHP versions 8.0 or higher.
*   `enableForDocComments`: `true` also applies the configured checks to type hints in doc-comment annotations. Disabled by default.
*   `withSpacesAroundOperators`: `yes` requires spaces around `|` and `&`, `no` requires no space around `|`and `&`. None is set by default so both are enabled.
*   `withSpacesInsideParentheses`: `yes` requires spaces inside parentheses, `no` requires no spaces inside parentheses. None is set by default so both are enabled.
*   `shortNullable`: `yes` requires usage of `?` for nullable type hint, `no` disallows it. None is set by default so both are enabled.
*   `nullPosition`: `first` requires `null` on first position in the type hint, `last` requires last position. None is set by default so `null` can be everywhere.
