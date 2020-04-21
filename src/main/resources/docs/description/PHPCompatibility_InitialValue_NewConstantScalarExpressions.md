Detect constant scalar expressions being used to set an initial value.Since PHP 5.6, it is now possible to provide a scalar expression involving
numeric and string literals and/or constants in contexts where PHP previously
expected a static value, such as constant and property declarations and
default values for function parameters.

PHP version 5.6