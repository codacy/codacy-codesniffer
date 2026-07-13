The opening brace of an OO structure must be on the line directly after the OO signature. The opening brace must be on a line by itself.

Valid: Opening brace on the correct line.
```
class Foo
{
}
```

Invalid: Opening brace on incorrect line.
```
class BraceOnSignatureLine {
}

class BlankLineBetween

{
}
```

Valid: Opening brace is on a line by itself.
```
class Foo
{
}
```

Invalid: Opening brace is not on a line by itself.
```
class Foo
{ public function __construct() {}
}
```

Valid: Opening brace indentation depth matches the OO structure signature's depth.
```
if (!class_exists('IndentedFourSpaces')) {
    abstract class IndentedFourSpaces
    {
    }
}
        
class NotIndented
{
}
```

Invalid: Opening brace indentation depth does not match the OO structure signature's depth.
```
if (!class_exists('IndentedFourSpaces')) {
    abstract class IndentedFourSpaces
{
}
}
        
class NotIndented
    {
    }
```
