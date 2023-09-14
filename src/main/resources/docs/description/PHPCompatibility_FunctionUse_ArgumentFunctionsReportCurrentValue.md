Functions inspecting function arguments report the current parameter value
instead of the original since PHP 7.0.
`func_get_arg()`, `func_get_args()`, `debug_backtrace()` and exception backtraces
will no longer report the original parameter value as was passed to the function,
but will instead provide the current value (which might have been modified). 

PHP version 7. 0