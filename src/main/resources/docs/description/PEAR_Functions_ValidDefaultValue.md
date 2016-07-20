Arguments with default values must be at the end of the argument list.
Instead of:

    function foo($x = null, $z, $y = null) {
     ..
    }

You should write:

    function foo($z, $x = null, $y = null) {
     ..
    }


      