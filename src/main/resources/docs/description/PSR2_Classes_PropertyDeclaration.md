Property names should not be prefixed with an underscore to indicate visibility.  Visibility should be used to declare properties rather than the var keyword.  Only one property should be declared within a statement.  The static declaration must come after the visibility declaration.

Valid: Correct property naming.
```
class Foo
{
    private $bar;
}
```

Invalid: An underscore prefix used to indicate visibility.
```
class Foo
{
    private $_bar;
}
```

Valid: Visibility of property declared.
```
class Foo
{
    private $bar;
}
```

Invalid: Var keyword used to declare property.
```
class Foo
{
    var $bar;
}
```

Valid: One property declared per statement.
```
class Foo
{
    private $bar;
    private $baz;
}
```

Invalid: Multiple properties declared in one statement.
```
class Foo
{
    private $bar, $baz;
}
```

Valid: If declared as static, the static declaration must come after the visibility declaration.
```
class Foo
{
    public static $bar;
    private $baz;
}
```

Invalid: Static declaration before the visibility declaration.
```
class Foo
{
    static protected $bar;
}
```
