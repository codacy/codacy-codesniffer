## Functions: Require Trailing Comma In Closure Use

Commas after the last inherited variable in multi-line `use` of closure declaration make adding a new variable easier and result in a cleaner versioning diff.

This sniff enforces trailing commas in multi-line declarations.

This sniff provides the following setting:

* `enable`: either to enable or not this sniff. By default, it is enabled for PHP versions 8.0 or higher.