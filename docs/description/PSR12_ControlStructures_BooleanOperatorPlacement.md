Boolean operators between conditions in control structures must always be at the beginning or at the end of the line, not a mix of both.

    This rule applies to if/else conditions, while loops and switch/match statements.

Valid: Boolean operator between conditions consistently at the beginning of the line.
```
if (
    $expr1
    && $expr2
    && ($expr3
| $expr4)
    && $expr5
) {
    // if body.
}
```

Invalid: Mix of boolean operators at the beginning and the end of the line.
```
if (
    $expr1 &&
    ($expr2 || $expr3)
    && $expr4
) {
    // if body.
}
```

Valid: Boolean operator between conditions consistently at the end of the line.
```
if (
    $expr1 ||
    ($expr2 || $expr3) &&
    $expr4
) {
    // if body.
}
```

Invalid: Mix of boolean operators at the beginning and the end of the line.
```
match (
    $expr1
    && $expr2 ||
    $expr3
) {
    // structure body.
};
```
