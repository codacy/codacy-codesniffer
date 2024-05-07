Yoda conditions are disallowed.

Valid: value to be asserted must go on the right side of the comparison.
```
if ($test === null) {
    $var = 1;
}
```

Invalid: value to be asserted must not be on the left.
```
if (null === $test) {
    $var = 1;
}
```
