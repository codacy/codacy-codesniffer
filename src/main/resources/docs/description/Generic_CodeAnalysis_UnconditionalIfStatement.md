If statements that are always evaluated should not be used.

Valid: An if statement that only executes conditionally.
```
if ($test) {
    $var = 1;
}
```

Invalid: An if statement that is always performed.
```
if (true) {
    $var = 1;
}
```

Valid: An if statement that only executes conditionally.
```
if ($test) {
    $var = 1;
}
```

Invalid: An if statement that is never performed.
```
if (false) {
    $var = 1;
}
```
