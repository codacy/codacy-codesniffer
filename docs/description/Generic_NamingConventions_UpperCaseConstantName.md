Constants should always be all-uppercase, with underscores to separate words.

Valid: All uppercase constant name.
```
define('FOO_CONSTANT', 'foo');

class FooClass
{
    const FOO_CONSTANT = 'foo';
}
```

Invalid: Mixed case or lowercase constant name.
```
define('Foo_Constant', 'foo');

class FooClass
{
    const foo_constant = 'foo';
}
```
