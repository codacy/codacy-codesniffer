PHP Code should pass the zend code analyzer.

Valid: Valid PHP Code.
```
function foo($bar, $baz)
{
    return $bar + $baz;
}
```

Invalid: There is an unused function parameter.
```
function foo($bar, $baz)
{
    return $bar + 2;
}
```
