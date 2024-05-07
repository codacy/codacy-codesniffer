All parameters in a functions signature should be used within the function.

Valid: All the parameters are used.
```
function addThree($a, $b, $c)
{
    return $a + $b + $c;
}
```

Invalid: One of the parameters is not being used.
```
function addThree($a, $b, $c)
{
    return $a + $b;
}
```
