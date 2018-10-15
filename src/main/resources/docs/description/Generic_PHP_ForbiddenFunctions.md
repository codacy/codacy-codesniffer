The forbidden functions sizeof() and delete() should not be used.

Valid: count() is used in place of sizeof().
```
$foo = count($bar);
```

Invalid: sizeof() is used.
```
$foo = sizeof($bar);
```
