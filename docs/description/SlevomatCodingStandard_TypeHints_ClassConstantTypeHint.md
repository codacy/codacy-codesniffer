## TypeHints: Class Constant Type Hint

*   Checks for missing typehints in case they can be declared natively.
*   Reports useless `@var` annotation (or whole documentation comment) because the type of constant is always clear.

Sniff provides the following settings:

*   `enableNativeTypeHint`: enforces native typehint. It's on by default if you're on PHP 8.3+
*   `fixableNativeTypeHint`: (default: `yes`) allows fixing native type hints. Use `no` to disable fixing, or `private` to fix only private constants (safer for inheritance/interface compatibility).
