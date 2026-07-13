There should be one space on either side of an equals sign used to assign a value to a variable. In the case of a block of related assignments, more space may be inserted to promote readability.

Valid: Equals signs aligned.
```
$shortVar        = (1 + 2);
$veryLongVarName = 'string';
$var             = foo($bar, $baz);
```

Invalid: Not aligned; harder to read.
```
$shortVar = (1 + 2);
$veryLongVarName = 'string';
$var = foo($bar, $baz);
```

Valid: Equals signs aligned; only one space after longest var name.
```
$shortVar       += 1;
$veryLongVarName = 1;
```

Invalid: Two spaces after longest var name.
```
$shortVar        += 1;
$veryLongVarName  = 1;
```

Valid: Equals signs aligned.
```
$shortVar         = 1;
$veryLongVarName -= 1;
```

Invalid: Equals signs not aligned.
```
$shortVar        = 1;
$veryLongVarName -= 1;
```
