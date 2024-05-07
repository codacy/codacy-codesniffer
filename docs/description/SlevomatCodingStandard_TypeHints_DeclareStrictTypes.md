## TypeHints: Declare Strict Types

Enforces having `declare(strict_types = 1)` at the top of each PHP file. Allows configuring how many newlines should be between the `<?php` opening tag and the `declare` statement.

Sniff provides the following settings:

*   `declareOnFirstLine`: requires `declare` on the first line right after `<?php`
*   `linesCountBeforeDeclare`: allows to set 0 to N lines to be between `declare` and previous statement. This option is ignored when `declareOnFirstLine` is enabled.
*   `linesCountAfterDeclare`: allows to set 0 to N lines to be between `declare` and next statement
*   `spacesCountAroundEqualsSign`: allows to set number of required spaces around the `=` operator
