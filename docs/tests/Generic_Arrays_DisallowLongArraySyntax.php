<?php

//#Patterns: Generic_Arrays_DisallowLongArraySyntax

    //#Info: Generic_Arrays_DisallowLongArraySyntax
    $var = array(
        'a' => 1,
    );

    function foo(array $array)
    {
        var_dump($array);
    }

    foo([
        'a' => 1,
    ]);

?>