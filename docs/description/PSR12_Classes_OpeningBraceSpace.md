The opening brace of an object-oriented construct must not be followed by a blank line.

Valid: No blank lines after opening brace.
```
class Foo
{
    public function bar()
    {
        // Method content.
    }
}
```

Invalid: Blank line after opening brace.
```
class Foo
{

    public function bar()
    {
        // Method content.
    }
}
```
