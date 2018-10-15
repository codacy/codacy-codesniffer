There should be exactly 1 space after the function keyword and 1 space on each side of the use keyword.  Closures should use the Kernighan/Ritchie Brace style and other single-line functions should use the BSD/Allman style.  Multi-line function declarations should have the parameter lists indented one level with the closing parenthesis on a newline followed by a single space and the opening brace of the function.

Valid: Correct spacing around function and use keywords.
```
$foo = function () use ($bar) {
};
```

Invalid: No spacing around function and use keywords.
```
$foo = function()use($bar){
};
```

Valid: Multi-line function declaration formatted properly.
```
function foo(
    $bar,
    $baz
) {
};
```

Invalid: Invalid indentation and formatting of closing parenthesis.
```
function foo(
$bar,
$baz)
{
};
```
