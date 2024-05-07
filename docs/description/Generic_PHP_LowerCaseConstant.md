The <em>true</em>, <em>false</em> and <em>null</em> constants must always be lowercase.

Valid: lowercase constants
```
if ($var === false || $var === null) {
    $var = true;
}
```

Invalid: uppercase constants
```
if ($var === FALSE || $var === NULL) {
    $var = TRUE;
}
```
