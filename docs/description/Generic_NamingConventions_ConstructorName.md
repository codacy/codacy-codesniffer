Constructors should be named __construct, not after the class.

Valid: The constructor is named __construct.
```
class Foo
{
    function __construct()
    {
    }
}
```

Invalid: The old style class name constructor is used.
```
class Foo
{
    function Foo()
    {
    }
}
```
