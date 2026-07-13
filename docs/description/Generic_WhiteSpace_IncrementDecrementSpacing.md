There should be no whitespace between variables and increment/decrement operators.

Valid: No whitespace between variables and increment/decrement operators.
```
++$i;
--$i['key']['id'];
ClassName::$prop++;
$obj->prop--;
```

Invalid: Whitespace between variables and increment/decrement operators.
```
++ $i;
--   $i['key']['id'];
ClassName::$prop    ++;
$obj->prop
--;
```
