Detect class member access on object instantiation/cloning.PHP 5.4: Class member access on instantiation has been added, e.g. `(new Foo)->bar()`.
PHP 7.0: Class member access on cloning has been added, e.g. `(clone $foo)->bar()`.

As of PHP 7.0, class member access on instantiation also works when using curly braces.
While unclear, this most likely has to do with the Uniform Variable Syntax changes.

PHP version 5.4
PHP version 7.0