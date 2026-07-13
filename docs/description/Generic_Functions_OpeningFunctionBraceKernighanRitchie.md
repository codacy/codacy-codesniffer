The function opening brace must be on the same line as the end of the function declaration, with
    exactly one space between the end of the declaration and the brace. The brace must be the last
    content on the line.

Valid: Opening brace on the same line.
```
function fooFunction($arg1, $arg2 = '') {
    // Do something.
}
```

Invalid: Opening brace on the next line.
```
function fooFunction($arg1, $arg2 = '')
{
    // Do something.
}
```
