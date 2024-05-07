<?php
//#Patterns: PHPCompatibility_Keywords_ForbiddenNames

/*
 * Use statements which are ok pre-PHP 5.6.
 */
namespace FooBar;
    use Foo\Bar;
    use Foobar as Baz;
    use Foobar as Baz, Bay as BarFoo;

class Foobar {
    use Baz;
}

class Foobar {
    use BazTrait {
        oldfunction as Baz
    }
}

class Foobar {
    use BazTrait {
        oldfunction as public Baz
    }
}

class Foobar {
    use BazTrait {
        oldfunction as protected Baz
    }
}

class Foobar {
    use BazTrait {
        oldfunction as private Baz
    }
}

class Foobar {
    use BazTrait {
        oldfunction as final Baz
    }
}

/*
 * PHP 5.6: Use statements using `const` and `function`
 */
use const Baz;
use const FOOBAR as Baz;
use function Baz;
use function FooBar as Baz;

class Foobar {
    use const Baz;
}

class Foobar {
    use function Baz;
}

trait Foobar {
    use const Baz;
}

trait Foobar {
    use function Baz;
}
