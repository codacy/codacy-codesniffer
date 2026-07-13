## Functions: Require Multi Line Call

Enforces function call to be split to more lines so each parameter is on its own line.

Sniff provides the following settings:

*   `minLineLength`: specifies min line length to enforce call to be split. Use 0 value to enforce for all calls, regardless of length.
*   `minParametersCount`: specifies min parameters count to enforce call to be split.
*   `excludedCallPatterns`: allows to configure which calls are excluded from sniff detection. This is an array of regular expressions (PCRE) with delimiters.
