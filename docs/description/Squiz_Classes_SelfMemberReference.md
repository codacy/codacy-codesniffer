The self keyword must be lowercase.

Valid: Lowercase self used.
```
class Bar {
    public function baz() {
        self::foo();
    }
}
```

Invalid: Uppercase self used.
```
class Bar {
    public function baz() {
        SELF::foo();
    }
}
```

Valid: No spaces around the double colon operator.
```
class Bar {
    public function baz() {
        self::foo();
    }
}
```

Invalid: Spaces around double colon operator.
```
class Bar {
    public function baz() {
        self :: foo();
    }
}
```

Valid: Self used as reference.
```
class Foo {
    public function bar() {}

    public function baz() {
        self::bar();
    }
}
```

Invalid: Local class name used as reference.
```
class Foo {
    public function bar() {}

    public function baz() {
        Foo::bar();
    }
}
```
