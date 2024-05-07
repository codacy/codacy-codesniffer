Multi-line if conditions should be indented one level and each line should begin with a boolean operator.  The end parenthesis should be on a new line.

Valid: Correct indentation.
```
if ($foo
    && $bar
) {
}
```

Invalid: No indentation used on the condition lines.
```
if ($foo
&& $bar
) {
}
```

Valid: Boolean operator at the start of the line.
```
if ($foo
    && $bar
) {
}
```

Invalid: Boolean operator at the end of the line.
```
if ($foo &&
    $bar
) {
}
```

Valid: End parenthesis on a new line.
```
if ($foo
    && $bar
) {
}
```

Invalid: End parenthesis not moved to a new line.
```
if ($foo
    && $bar) {
}
```
