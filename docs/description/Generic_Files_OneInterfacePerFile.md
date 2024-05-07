There should only be one interface defined in a file.

Valid: Only one interface in the file.
```
<?php
interface Foo
{
}
```

Invalid: Multiple interfaces defined in one file.
```
<?php
interface Foo
{
}

interface Bar
{
}
```
