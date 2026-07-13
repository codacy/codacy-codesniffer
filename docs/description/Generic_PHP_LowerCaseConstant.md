The <em>true</em>, <em>false</em> and <em>null</em> constants must always be lowercase.

Valid: Lowercase constants.
```
if ($var === false || $var === null) {
    $var = true;
}
```

Invalid: Uppercase constants.
```
if ($var === FALSE || $var === NULL) {
    $var = TRUE;
}
```
