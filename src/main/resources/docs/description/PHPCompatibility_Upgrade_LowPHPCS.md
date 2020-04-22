Add a notification for users of low PHPCS versions.
Originally PHPCompatibility supported PHPCS 1. 5. x, 2. x and since PHPCompatibility 8. 0. 0, 3. x. 
Support for PHPCS < 2. 3. 0 has been dropped in PHPCompatibility 9. 0. 0. 

The standard will - up to a point - still work for users of lower
PHPCS versions, but will give less accurate results and may throw
notices and warnings (or even fatal out). 

This sniff adds an explicit error/warning for users of the standard
using a PHPCS version below the recommended version.