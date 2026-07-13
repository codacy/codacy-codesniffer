For function and closure return type declarations, there must be one space after the colon followed by the type declaration, and no space before the colon.

    The colon and the return type declaration have to be on the same line as the argument list closing parenthesis.

Valid: A single space between the colon and type in a return type declaration.
```
$closure = function ( $arg ): string {
   // Closure body.
};
```

Invalid: No space between the colon and the type in a return type declaration.
```
$closure = function ( $arg ):string {
   // Closure body.
};
```

Valid: No space before the colon in a return type declaration.
```
function someFunction( $arg ): string {
   // Function body.
};
```

Invalid: One or more spaces before the colon in a return type declaration.
```
function someFunction( $arg )   : string {
   // Function body.
};
```
