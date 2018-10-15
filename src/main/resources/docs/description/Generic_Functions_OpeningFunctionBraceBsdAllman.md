Function declarations follow the "BSD/Allman style". The function brace is on the line following the function declaration and is indented to the same column as the start of the function declaration.

Valid: brace on next line
```
function fooFunction($arg1, $arg2 = '')
{
    ...
}
```

Invalid: brace on same line
```
function fooFunction($arg1, $arg2 = '') {
    ...
}
```
