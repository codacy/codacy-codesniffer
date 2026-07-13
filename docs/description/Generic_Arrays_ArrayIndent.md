The opening brace of a multi-line array must be indented at least to the same level as the start of the statement.

Valid: Opening brace of a multi-line array indented to the same level as the start of the statement.
```
$b = [
    1,
    2,
];

if ($condition) {
    $a =
    [
        1,
        2,
    ];
}
```

Invalid: Opening brace of a multi-line array not indented to the same level as the start of the statement.
```
if ($condition) {
    $a =
[
        1,
        2,
    ];
}
```

Valid: Each array element is indented by exactly four spaces.
```
$a = array(
    1,
    2,
    3,
);
```

Invalid: Array elements not indented by four spaces.
```
$a = array(
  1,
     2,
        3,
);
```

Valid: Array closing brace on its own line.
```
$a = [
    1,
    2,
];
```

Invalid: Array closing brace not on its own line.
```
$a = [
    1,
    2,];
```

Valid: Closing brace aligned with the start of the statement containing the array opener.
```
$a = array(
    1,
    2,
);
```

Invalid: Closing brace not aligned with the start of the statement containing the array opener.
```
$a = array(
    1,
    2,
  );
```
