The PHP_SAPI constant should be used instead of php_sapi_name().

Valid: PHP_SAPI is used.
```
if (PHP_SAPI === 'cli') {
    echo "Hello, CLI user.";
}
```

Invalid: php_sapi_name() is used.
```
if (php_sapi_name() === 'cli') {
    echo "Hello, CLI user.";
}
```
