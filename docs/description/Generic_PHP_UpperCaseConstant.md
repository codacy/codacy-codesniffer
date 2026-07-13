The <em>true</em>, <em>false</em> and <em>null</em> constants must always be uppercase.

Valid: Uppercase constants.
```
if ($var === FALSE || $var === NULL) {
    $var = TRUE;
}
```

Invalid: Lowercase constants.
```
if ($var === false || $var === null) {
    $var = true;
}
```
