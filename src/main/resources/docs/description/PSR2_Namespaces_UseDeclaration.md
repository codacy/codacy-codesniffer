Each use declaration must contain only one namespace and must come after the first namespace declaration.  There should be one blank line after the final use statement.

Valid: One use declaration per namespace.
```
use \Foo;
use \Bar;
```

Invalid: Multiple namespaces in a use declaration.
```
use \Foo, \Bar;
```

Valid: Use statements come after first namespace.
```
namespace \Foo;

use \Bar;
```

Invalid: Namespace declared after use.
```
use \Bar;

namespace \Foo;
```

Valid: A single blank line after the final use statement.
```
use \Foo;
use \Bar;

class Baz
{
}
```

Invalid: No blank line after the final use statement.
```
use \Foo;
use \Bar;
class Baz
{
}
```
