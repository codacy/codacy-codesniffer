## Functions: Unused Parameter

Looks for unused parameters.

This sniff provides the following setting:

*   `allowedParameterPatterns`: allows to configure which parameters are always allowed, even if unused. This is an array of regular expressions (PCRE) with delimiters, but without the leading `$` from variable names. (For example, use `[/^_/]` to allow parameters that start with an underscore, like `$_unused`.)
