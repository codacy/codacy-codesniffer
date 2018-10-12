Arbitrary sets of parentheses should have no spaces inside.

Valid: no spaces on the inside of a set of arbitrary parentheses.
```
$a = (null !== $extra);
```

Invalid: spaces on the inside of a set of arbitrary parentheses.
```
$a = ( null !== $extra );
```

Invalid: new lines on the inside of a set of arbitrary parentheses.
```
$a = (
    null !== $extra
);
```
