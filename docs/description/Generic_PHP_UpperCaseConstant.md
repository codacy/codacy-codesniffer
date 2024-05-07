The <em>true</em>, <em>false</em> and <em>null</em> constants must always be uppercase.

Valid: uppercase constants
```
if ($var === FALSE || $var === NULL) {
    $var = TRUE;
}
```

Invalid: lowercase constants
```
if ($var === false || $var === null) {
    $var = true;
}
```
