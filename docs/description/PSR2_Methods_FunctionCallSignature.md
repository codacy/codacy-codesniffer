Checks that the function call format is correct.

Valid: Correct spacing is used around parentheses.
```
foo($bar, $baz);
```

Invalid: Incorrect spacing used, too much space around the parentheses.
```
foo ( $bar, $baz );
```

Valid: Correct number of spaces used for indent in a multi-line function call.
```
foo(
    $bar,
    $baz
);
```

Invalid: Incorrect number of spaces used for indent in a multi-line function call.
```
foo(
  $bar,
      $baz
);
```

Valid: Closing parenthesis for a multi-line function call is on a new line after the last parameter.
```
foo(
    $bar,
    $baz
);
```

Invalid: Closing parenthesis for a multi-line function call is not on a new line after the last parameter.
```
foo(
    $bar,
    $baz);
```

Valid: The first argument of a multi-line function call is on a new line.
```
foo(
    $bar,
    $baz
);
```

Invalid: The first argument of a multi-line function call is not on a new line.
```
foo($bar,
    $baz
);
```

Valid: Only one argument per line in a multi-line function call.
```
foo(
    $bar,
    $baz
);
```

Invalid: Two or more arguments per line in a multi-line function call.
```
foo(
    $bar, $baz
);
```

Valid: No blank lines in a multi-line function call.
```
foo(
    $bar,
    $baz
);
```

Invalid: Blank line in multi-line function call.
```
foo(
    $bar,

    $baz
);
```
