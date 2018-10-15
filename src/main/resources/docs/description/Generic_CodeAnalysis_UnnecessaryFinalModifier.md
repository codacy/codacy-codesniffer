Methods should not be declared final inside of classes that are declared final.

Valid: A method in a final class is not marked final.
```
final class Foo
{
    public function bar()
    {
    }
}
```

Invalid: A method in a final class is also marked final.
```
final class Foo
{
    public final function bar()
    {
    }
}
```
