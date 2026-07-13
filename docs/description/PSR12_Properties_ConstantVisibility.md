Visibility must be declared on all class constants if your project PHP minimum version supports constant visibilities (PHP 7.1 or later).

    The term "class" refers to all classes, interfaces, enums and traits.

Valid: Constant visibility declared.
```
class Foo
{
    private const BAR = 'bar';
}
```

Invalid: Constant visibility not declared.
```
class Foo
{
    const BAR = 'bar';
}
```
