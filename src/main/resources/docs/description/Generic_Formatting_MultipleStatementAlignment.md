There should be one space on either side of an equals sign used to assign a value to a variable. In the case of a block of related assignments, more space may be inserted to promote readability.

Equals signs aligned
```
$shortVar        = (1 + 2);
$veryLongVarName = 'string';
$var             = foo($bar, $baz, $quux);
```

Not aligned; harder to read
```
$shortVar = (1 + 2);
$veryLongVarName = 'string';
$var = foo($bar, $baz, $quux);
```

Equals signs aligned; only one space after longest var name
```
$shortVar       += 1;
$veryLongVarName = 1;
```

Two spaces after longest var name
```
$shortVar        += 1;
$veryLongVarName  = 1;
```

Equals signs aligned
```
$shortVar         = 1;
$veryLongVarName -= 1;
```

Equals signs not aligned
```
$shortVar        = 1;
$veryLongVarName -= 1;
```
