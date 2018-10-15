//#Patterns: PEAR_Functions_ValidDefaultValue

<?php

    function foo($z, $x = null, $y = null) {

    }

    //#Err: PEAR_Functions_ValidDefaultValue
    function connect($persistent = false, $dsn) {

    }

?>