Detect and verify the use of parameter type declarations in function declarations.
Parameter type declarations - class/interface names only - is available since PHP 5. 0. 
- Since PHP 5. 1, the `array` keyword can be used. 
- Since PHP 5. 2, `self` and `parent` can be used.  Previously, those were interpreted as
  class names. 
- Since PHP 5. 4, the `callable` keyword. 
- Since PHP 7. 0, scalar type declarations are available. 
- Since PHP 7. 1, the `iterable` pseudo-type is available. 
- Since PHP 7. 2, the generic `object` type is available. 

Additionally, this sniff does a cursory check for typical invalid type declarations,
such as:
- `boolean` (should be `bool`), `integer` (should be `int`) and `static`. 
- `self`/`parent` as type declaration used outside class context throws a fatal error since PHP 7. 0. 

PHP version 5. 0+