Constants should always be all-uppercase, with underscores to separate words.

Valid: all uppercase
```
define('FOO_CONSTANT', 'foo');

class FooClass
{
    const FOO_CONSTANT = 'foo';
}
```

Invalid: mixed case
```
define('Foo_Constant', 'foo');

class FooClass
{
    const foo_constant = 'foo';
}
```
