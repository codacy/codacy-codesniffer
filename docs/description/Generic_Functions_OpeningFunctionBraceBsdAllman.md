Function declarations must follow the "BSD/Allman style". The opening brace is on the line
    following the function declaration and is indented to the same column as the start of the
    function declaration. The brace must be the last content on the line.

Valid: Opening brace on the next line.
```
function fooFunction($arg1, $arg2 = '')
{
    // Do something
}
```

Invalid: Opening brace on the same line.
```
function fooFunction($arg1, $arg2 = '') {
    // Do something
}
```
