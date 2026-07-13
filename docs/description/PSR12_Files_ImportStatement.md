Import use statements must not begin with a leading backslash.

Valid: Import statement doesn't begin with a leading backslash.
```
<?php

use Vendor\Package\ClassA as A;

class FooBar extends A
{
    // Class content.
}
```

Invalid: Import statement begins with a leading backslash.
```
<?php

use \Vendor\Package\ClassA as A;

class FooBar extends A
{
    // Class content.
}
```
