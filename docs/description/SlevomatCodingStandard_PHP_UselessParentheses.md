## PHP: Useless Parentheses

Looks for useless parentheses.

Sniff provides the following settings:

*   `ignoreComplexTernaryConditions` (default: `false`): ignores complex ternary conditions - condition must contain `&&`, `||` etc. or end of line.
*   `enableCheckAroundNew` (default: `false`): enables check of useless parentheses around `(new class())->call()`.
