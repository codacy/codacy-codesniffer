A php file should either contain declarations with no side effects, or should just have logic (including side effects) with no declarations.

Valid: A class defined in a file by itself.
```
<?php
class Foo
{
}
```

Invalid: A class defined in a file with other code.
```
<?php
class Foo
{
}

echo "Class Foo loaded."
```
