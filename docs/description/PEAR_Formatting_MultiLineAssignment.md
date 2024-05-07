Multi-line assignment should have the equals sign be the first item on the second line indented correctly.

Valid: Assignment operator at the start of the second line.
```
$foo
    = $bar;
```

Invalid: Assignment operator at end of first line.
```
$foo =
    $bar;
```

Valid: Assignment operator indented one level.
```
$foo
    = $bar;
```

Invalid: Assignment operator not indented.
```
$foo
= $bar;
```
