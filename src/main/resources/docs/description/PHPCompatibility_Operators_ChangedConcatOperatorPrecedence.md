Detect code affected by the change in operator precedence of concatenation in PHP 8.0.In PHP < 8.0 the operator precedence of `.`, `+` and `-` are the same.
As of PHP 8.0, the operator precedence of the concatenation operator will be
lowered to be right below the `<<` and `>>` operators.

As of PHP 7.4, a deprecation warning will be thrown upon encountering an
unparenthesized expression containing an `.` before a `+` or `-`.

PHP version 7.4
PHP version 8.0