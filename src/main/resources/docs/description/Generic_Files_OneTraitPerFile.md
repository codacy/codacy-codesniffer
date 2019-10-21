There should only be one trait defined in a file.

Valid: Only one trait in the file.
```
<?php
trait Foo
{
}
```

Invalid: Multiple traits defined in one file.
```
<?php
trait Foo
{
}

trait Bar
{
}
```
