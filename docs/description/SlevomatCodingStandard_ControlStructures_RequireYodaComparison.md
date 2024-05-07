## ControlStructures: Require Yoda Comparison

[Yoda conditions](https://en.wikipedia.org/wiki/Yoda_conditions) decrease code comprehensibility and readability by switching operands around comparison operators forcing the reader to read the code in an unnatural way.

Sniff provides the following settings:

*   `alwaysVariableOnRight` (defaults to `false`): moves variables always to right.

`DisallowYodaComparison` looks for and fixes such comparisons not only in `if` statements but in the whole code.

However, if you prefer Yoda conditions, you can use `RequireYodaComparison`.
