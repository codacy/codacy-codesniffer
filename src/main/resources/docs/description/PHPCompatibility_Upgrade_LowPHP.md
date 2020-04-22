Add a notification for users of low PHP versions.
Originally PHPCompatibility supported PHP 5. 1 and higher. 
As of PHPCompatibility 8. 0. 0, support for PHP < 5. 3 has been dropped. 

The intention is to drop support for PHP 5. 3 in the (near) future. 

This sniff adds an explicit error/warning for users of the standard
using a PHP version below the recommended version.