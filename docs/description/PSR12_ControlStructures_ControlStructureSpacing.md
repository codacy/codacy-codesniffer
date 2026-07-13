Single line control structures must have no spaces after the condition opening parenthesis and before the condition closing parenthesis.

Valid: No space after the opening parenthesis in a single-line condition.
```
if ($expr) {
}
```

Invalid: Space after the opening parenthesis in a single-line condition.
```
if ( $expr) {
}
```

Valid: No space before the closing parenthesis in a single-line condition.
```
if ($expr) {
}
```

Invalid: Space before the closing parenthesis in a single-line condition.
```
if ($expr ) {
}
```

Valid: First expression of a multi-line control structure condition block is on the line after the opening parenthesis.
```
while (
    $expr1
    && $expr2
) {
}
```

Invalid: First expression of a multi-line control structure condition block is on the same line as the opening parenthesis.
```
while ($expr1
    && $expr2
) {
}
```

Valid: Each line in a multi-line control structure condition block indented at least once. Default indentation is 4 spaces.
```
while (
    $expr1
    && $expr2
) {
}
```

Invalid: Some lines in a multi-line control structure condition block not indented correctly.
```
while (
$expr1
    && $expr2
  && $expr3
) {
}
```

Valid: The closing parenthesis of a multi-line control structure condition block is on the line after the last expression.
```
while (
    $expr1
    && $expr2
) {
}
```

Invalid: The closing parenthesis of a multi-line control structure condition block is on the same line as the last expression.
```
while (
    $expr1
    && $expr2) {
}
```

Valid: The closing parenthesis of a multi-line control structure condition block is indented to the same level as start of the control structure.
```
while (
    $expr1
    && $expr2
) {
}
```

Invalid: The closing parenthesis of a multi-line control structure condition block is not indented to the same level as start of the control structure.
```
while (
    $expr1
    && $expr2
  ) {
}
```
