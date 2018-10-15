Function arguments should have one space after a comma, and single spaces surrounding the equals sign for default values.

Valid: Single spaces after a comma.
```
function foo($bar, $baz)
{
}
```

Invalid: No spaces after a comma.
```
function foo($bar,$baz)
{
}
```

Valid: Single spaces around an equals sign in function declaration.
```
function foo($bar, $baz = true)
{
}
```

Invalid: No spaces around an equals sign in function declaration.
```
function foo($bar, $baz=true)
{
}
```
