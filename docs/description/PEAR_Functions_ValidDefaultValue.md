Arguments with default values go at the end of the argument list.

Valid: Argument with default value at end of declaration.
```
function connect($dsn, $persistent = false)
{
    ...
}
```

Invalid: Argument with default value at start of declaration.
```
function connect($persistent = false, $dsn)
{
    ...
}
```
