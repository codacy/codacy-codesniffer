Class and Interface names should be unique in a project.  They should never be duplicated.

Valid: A unique class name.
```
class Foo
{
}
```

Invalid: A class duplicated (including across multiple files).
```
class Foo
{
}

class Foo
{
}
```
