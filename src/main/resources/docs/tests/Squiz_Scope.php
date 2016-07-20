//#Patterns: Squiz_Scope_MethodScope

<?php

class Example
{
    const TABS = 4;

    public function spaces($something)
    {
        if ($something) echo 'hello';

        if ($something) {
            echo 'hello';
        }

    }

    private function spaces2($something)
    {
        if ($something) echo 'hello';

        if ($something) {
            echo 'hello';
        }

    }

    //#Info: Squiz_Scope_MethodScope
    function spaces3($something)
    {
        if ($something) echo 'hello';

        if ($something) {
            echo 'hello';
        }

    }
}

?>