As of PHP 7.4, `proc_open()` now also accepts an array instead of a string for the command.
In that case, the process will be opened directly (without going through a shell) and
PHP will take care of any necessary argument escaping. 

PHP version 7. 4