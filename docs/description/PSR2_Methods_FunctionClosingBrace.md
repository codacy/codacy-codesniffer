Checks that the closing brace of a function goes directly after the body.

Valid: Closing brace directly follows the function body.
```
function foo()
{
    echo 'foo';
}
```

Invalid: Blank line between the function body and the closing brace.
```
function foo()
{
    echo 'foo';

}
```
