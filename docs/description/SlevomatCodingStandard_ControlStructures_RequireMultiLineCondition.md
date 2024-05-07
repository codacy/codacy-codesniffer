## ControlStructures: Require Multi Line Condition

Enforces conditions of `if`, `elseif`, `while` and `do-while` with one or more boolean operators to be split to more lines
so each condition part is on its own line.

Sniff provides the following settings:

*   `minLineLength`: specifies minimum line length to enforce condition to be split. Use 0 value to enforce for all conditions, regardless of length.
*   `booleanOperatorOnPreviousLine`: boolean operator is placed at the end of previous line when fixing.
*   `alwaysSplitAllConditionParts`: require all condition parts to be on its own line - it reports error even if condition is already multi-line but there are some condition parts on the same line.
