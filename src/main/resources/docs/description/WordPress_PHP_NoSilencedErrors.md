Discourage the use of the PHP error silencing operator.
This sniff allows the error operator to be used with a select list
of whitelisted functions, as no amount of error checking can prevent
PHP from throwing errors when those functions are used.