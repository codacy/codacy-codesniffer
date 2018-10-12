FIXME Statements should be taken care of.

Valid: A comment without a fixme.
```
// Handle strange case
if ($test) {
    $var = 1;
}
```

Invalid: A fixme comment.
```
// FIXME: This needs to be fixed!
if ($test) {
    $var = 1;
}
```
