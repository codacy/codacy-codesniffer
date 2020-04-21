Detect declarations of PHP 4 style constructors which are deprecated as of PHP 7.0.0.PHP 4 style constructors - methods that have the same name as the class they are defined in -
are deprecated as of PHP 7.0.0, and will be removed in the future.
PHP 7 will emit `E_DEPRECATED` if a PHP 4 constructor is the only constructor defined
within a class. Classes that implement a `__construct()` method are unaffected.

Note: Methods with the same name as the class they are defined in _within a namespace_
are not recognized as constructors anyway and therefore outside the scope of this sniff.

PHP version 7.0