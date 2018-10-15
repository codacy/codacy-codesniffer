Call-time pass-by-reference is not allowed. It should be declared in the function definition.

Valid: Pass-by-reference is specified in the function definition.
```
function foo(&$bar)
{
    $bar++;
}

$baz = 1;
foo($baz);
```

Invalid: Pass-by-reference is done in the call to a function.
```
function foo($bar)
{
    $bar++;
}

$baz = 1;
foo(&$baz);
```
