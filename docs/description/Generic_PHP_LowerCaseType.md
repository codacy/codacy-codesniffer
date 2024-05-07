All PHP types used for parameter type and return type declarations should be lowercase.

Valid: Lowercase type declarations used.
```
function myFunction(int $foo) : string {
}
```

Invalid: Non-lowercase type declarations used.
```
function myFunction(Int $foo) : STRING {
}
```

Valid: Lowercase type used.
```
$foo = (bool) $isValid;
```

Invalid: Non-lowercase type used.
```
$foo = (BOOL) $isValid;
```
