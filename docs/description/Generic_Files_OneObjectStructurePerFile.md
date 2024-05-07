There should only be one class or interface or trait defined in a file.

Valid: Only one object structure in the file.
```
<?php
trait Foo
{
}
```

Invalid: Multiple object structures defined in one file.
```
<?php
trait Foo
{
}

class Bar
{
}
```
