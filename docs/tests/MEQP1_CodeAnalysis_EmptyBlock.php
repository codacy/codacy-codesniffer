<?php
//#Patterns: MEQP1_CodeAnalysis_EmptyBlock

//#Info: MEQP1_CodeAnalysis_EmptyBlock
switch ($foo) {
    // Empty switch statement body
}
switch ($foo) {
    case 'bar':
        break;
    default:
        break;
}
//#Info: MEQP1_CodeAnalysis_EmptyBlock
if ($foo) {
    // Just a comment
    //#Info: MEQP1_CodeAnalysis_EmptyBlock
} elseif ($bar) {
    // Yet another comment
    //#Info: MEQP1_CodeAnalysis_EmptyBlock
} else {
}
if ($foo) {
    $foo = 'bar';
} else if ($bar) {
    $bar = 'foo';
}
for ($i = 0; $i < 10; $i++) {
    //#Info: MEQP1_CodeAnalysis_EmptyBlock
    for ($j = 0; $j < 10; $j++) {
        // Just a comment
    }
}
//#Info: MEQP1_CodeAnalysis_EmptyBlock
foreach ($foo as $bar) {}
foreach ($foo as $bar) {
    $bar *= 2;
}
//#Info: MEQP1_CodeAnalysis_EmptyBlock
do {
    // Just a comment
    // Just another comment
} while ($foo);
do {
    //#Info: MEQP1_CodeAnalysis_EmptyBlock
    while ($bar) {
    }
} while (true);
//#Info: MEQP1_CodeAnalysis_EmptyBlock
while ($foo) { /* Comment in the same line */}
while ($foo) {
    //#Info: MEQP1_CodeAnalysis_EmptyBlock
    try {
    } catch (Exception $e) {
        echo $e->getTraceAsString();
    }
}
try {
    throw Exception('Error...');
    //#Info: MEQP1_CodeAnalysis_EmptyBlock
} catch (Exception $e) {}
try {
    throw Exception('Error...');
    //#Info: MEQP1_CodeAnalysis_EmptyBlock
} catch (Exception $e) {
    // TODO: Handle this exception later :-)
}
//#Info: MEQP1_CodeAnalysis_EmptyBlock
//#Info: MEQP1_CodeAnalysis_EmptyBlock
if (true) {} elseif (false) {}
//#Info: MEQP1_CodeAnalysis_EmptyBlock
class EmptyBlockTestInc { /*Empty class block*/ }
class EmptyBlockTestIncTrue
{
    public $field;
}
//#Info: MEQP1_CodeAnalysis_EmptyBlock
abstract class EmptyBlockTestIncAbstract { /*Empty class block*/ }
abstract class EmptyBlockTestIncTrueAbstract
{
    public $field;
}
//#Info: MEQP1_CodeAnalysis_EmptyBlock
function emptyBlock () { /*Empty function block*/ }
function emptyBlockT () { return true; }
//#Info: MEQP1_CodeAnalysis_EmptyBlock
interface EmptyBlockInterface { /*Empty interface block*/ }
interface EmptyBlockInterfaceTrue {
    public function test();
}
//#Info: MEQP1_CodeAnalysis_EmptyBlock
trait EmptyBlockTrait { /*Empty trait block*/ }
trait EmptyBlockTraitTrue {
    function test() {
        return true;
    }
}
