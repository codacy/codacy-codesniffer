There should be exactly one space before a cast operator.

Valid: Single space before a cast operator.
```
$integer = (int) $string;
$c = $a . (string) $b;
```

Invalid: No space or multiple spaces before a cast operator.
```
$integer =(int) $string;
$c = $a .   (string) $b;
```
