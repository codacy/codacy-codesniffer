Detect usage of `func_get_args()`, `func_get_arg()` and `func_num_args()` in invalid context.Checks for:
- Prior to PHP 5.3, these functions could not be used as a function call parameter.
- Calling these functions from the outermost scope of a file which has been included by
  calling `include` or `require` from within a function in the calling file, worked
  prior to PHP 5.3. As of PHP 5.3, this will generate a warning and will always return false/-1.
  If the file was called directly or included in the global scope, calls to these
  functions would already generate a warning prior to PHP 5.3.

PHP version 5.3