//#Patterns: Generic_ControlStructures_InlineControlStructure

<?php

class Example
{
    const TABS = 4;

    public function spaces($something)
    {
        //#Info: Generic_ControlStructures_InlineControlStructure
        if ($something) echo 'hello';

        if ($something) {
            echo 'hello';
        }

    }
}

?>