Static methods should not use $this.

Valid: Using self:: to access static variables.
```
class Foo
{
    static function bar()
    {
        return self::$staticMember;
    }
}
```

Invalid: Using $this-> to access static variables.
```
class Foo
{
    static function bar()
    {
    return $this->$staticMember;
    }
}
```
