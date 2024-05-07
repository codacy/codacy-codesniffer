Methods should not be defined that only call the parent method.

Valid: A method that extends functionality on a parent method.
```
final class Foo
{
    public function bar()
    {
        parent::bar();
        $this->doSomethingElse();
    }
}
```

Invalid: An overriding method that only calls the parent.
```
final class Foo
{
    public function bar()
    {
        parent::bar();
    }
}
```
