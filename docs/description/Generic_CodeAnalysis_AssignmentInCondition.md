Variable assignments should not be made within conditions.

Valid: A variable comparison being executed within a condition.
```
if ($test === 'abc') {
    // Code.
}
```

Invalid: A variable assignment being made within a condition.
```
if ($test = 'abc') {
    // Code.
}
```
