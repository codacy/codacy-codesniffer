Method names should not be prefixed with an underscore to indicate visibility.  The static keyword, when present, should come after the visibility declaration, and the final and abstract keywords should come before.

Valid: Correct method naming.
```
class Foo
{
    private function bar()
    {
    }
}
```

Invalid: An underscore prefix used to indicate visibility.
```
class Foo
{
    private function _bar()
    {
    }
}
```

Valid: Correct ordering of method prefixes.
```
class Foo
{
    final public static function bar()
    {
    }
}
```

Invalid: static keyword used before visibility and final used after.
```
class Foo
{
    static public final function bar()
    {
    }
}
```
