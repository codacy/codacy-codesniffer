Any scope openers except for switch statements should be indented 1 level.  This includes classes, functions, and control structures.

Valid: Consistent indentation level for scope.
```
function foo()
{
    if ($test) {
        $var = 1;
    }
}
```

Invalid: Indentation is not used for scope.
```
function foo()
{
if ($test) {
$var = 1;
}
}
```
