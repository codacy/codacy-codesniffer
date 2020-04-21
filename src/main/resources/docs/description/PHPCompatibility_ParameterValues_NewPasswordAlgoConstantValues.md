The constant value of the password hash algorithm constants has changed in PHP 7.4.Applications using the constants `PASSWORD_DEFAULT`, `PASSWORD_BCRYPT`,
`PASSWORD_ARGON2I`, and `PASSWORD_ARGON2ID` will continue to function correctly.
Using an integer will still work, but will produce a deprecation warning.

PHP version 7.4