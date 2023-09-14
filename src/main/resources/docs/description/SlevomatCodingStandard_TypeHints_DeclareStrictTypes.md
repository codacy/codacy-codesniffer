# SlevomatCodingStandard_TypeHints_DeclareStrictTypes

Enforces having `declare(strict_types = 1)` at the top of each PHP file. Allows configuring how many newlines should be between the `<?php` opening tag and the `declare` statement.

Sniff provides the following settings:

* `newlinesCountBetweenOpenTagAndDeclare`: allows to set 0 to N newlines to be between `<?php` and `declare`
* `newlinesCountAfterDeclare`: allows to set 0 to N newlines to be between `declare` and next statement
* `spacesCountAroundEqualsSign`: allows to set number of required spaces around the `=` operator
