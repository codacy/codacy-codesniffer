Detect variable names forbidden to be used in closure `use` statements.
Variables bound to a closure via the `use` construct cannot use the same name
as any superglobals, `$this`, or any parameter since PHP 7. 1. 

PHP version 7. 1