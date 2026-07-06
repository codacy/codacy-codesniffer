## Functions: Require Single Line Call

Enforces function call to be on a single line.

Sniff provides the following settings:

*   `maxLineLength`: specifies max allowed line length. If call would fit on it, it's enforced. Use 0 value to enforce for all calls, regardless of length.
*   `ignoreWithComplexParameter` (default: `true`): ignores calls with arrays, closures, arrow functions and nested calls.
