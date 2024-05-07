Each class must be in a file by itself and must be under a namespace (a top-level vendor name).

Valid: One class in a file.
```
<?php
namespace Foo;

class Bar {
}
```

Invalid: Multiple classes in a single file.
```
<?php
namespace Foo;

class Bar {
}

class Baz {
}
```

Valid: A vendor-level namespace is used.
```
<?php
namespace Foo;

class Bar {
}
```

Invalid: No namespace used in file.
```
<?php
class Bar {
}
```
