## ControlStructures: Require Multi Line Ternary Operator

Ternary operator has to be reformatted to more lines when the line length exceeds the given limit.

Sniff provides the following settings:

*   `lineLengthLimit` (defaults to `0`)
*   `minExpressionsLength` (defaults to `null`): when the expressions after `?` are shorter than this length, the ternary operator does not have to be reformatted.
