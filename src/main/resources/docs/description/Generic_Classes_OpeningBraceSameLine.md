The opening brace of a class must be on the same line after the definition and must be the last thing on that line.

Valid: Opening brace on the same line.
```
class Foo {
}
```

Invalid: Opening brace on the next line.
```
class Foo
{
}
```

Valid: Opening brace is the last thing on the line.
```
class Foo {
}
```

Invalid: Opening brace not last thing on the line.
```
class Foo { // Start of class.
}
```
