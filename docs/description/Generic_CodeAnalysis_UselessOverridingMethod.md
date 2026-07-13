It is discouraged to override a method if the overriding method only calls the parent method.

Valid: A method that extends functionality of a parent method.
```
final class Foo extends Baz
{
    public function bar()
    {
        parent::bar();
        $this->doSomethingElse();
    }
}
```

Invalid: An overriding method that only calls the parent method.
```
final class Foo extends Baz
{
    public function bar()
    {
        parent::bar();
    }
}
```
