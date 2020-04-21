Check for the use of deprecated and removed regex modifiers for PCRE regex functions.Initially just checks for the `e` modifier, which was deprecated since PHP 5.5
and removed as of PHP 7.0.

{@internal If and when this sniff would need to start checking for other modifiers, a minor
refactor will be needed as all references to the `e` modifier are currently hard-coded.}

PHP version 5.5
PHP version 7.0