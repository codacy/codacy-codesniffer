# SlevomatCodingStandard_ControlStructures_RequireSingleLineCondition

Enforces conditions of `if`, `elseif`, `while` and `do-while` to be on a single line.

Sniff provides the following settings:

* `maxLineLength`: specifies max allowed line length. If conditition (and the rest of the line) would fit on it, it's enforced. Use 0 value to enforce for all conditions, regardless of length.
* `alwaysForSimpleConditions`: allows to enforce single line for all simple conditions (i.e no `&&`, `||` or `xor`), regardless of length.
