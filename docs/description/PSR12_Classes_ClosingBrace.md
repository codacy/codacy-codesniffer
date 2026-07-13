The closing brace of object-oriented constructs and functions must not be followed by any comment or statement on the same line.

Valid: Closing brace is the last content on the line.
```
class Foo
{
    // Class content.
}

function bar()
{
    // Function content.
}
```

Invalid: Comment or statement following the closing brace on the same line.
```
interface Foo2
{
    // Interface content.
} echo 'Hello!';

function bar()
{
    // Function content.
} //end bar()
```
