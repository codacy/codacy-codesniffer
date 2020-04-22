Detect direct calls to the `__clone()` magic method, which is allowed since PHP 7.0.
"Doing calls like `$obj->__clone()` is now allowed.  This was the only magic method
 that had a compile-time check preventing some calls to it, which doesn't make sense. 
 If we allow all other magic methods to be called, there's no reason to forbid this one. "

PHP version 7. 0