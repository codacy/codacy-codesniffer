The self keyword should be used instead of the current class name, should be lowercase, and should not have spaces before or after it.

Valid: Lowercase self used.
```
self::foo();
```

Invalid: Uppercase self used.
```
SELF::foo();
```

Valid: Correct spacing used.
```
self::foo();
```

Invalid: Incorrect spacing used.
```
self :: foo();
```

Valid: Self used as reference.
```
class Foo
{
    public static function bar()
    {
    }

    public static function baz()
    {
        self::bar();
    }
}
```

Invalid: Local class name used as reference.
```
class Foo
{
    public static function bar()
    {
    }

    public static function baz()
    {
        Foo::bar();
    }
}
```
