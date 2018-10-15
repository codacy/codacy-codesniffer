//#Patterns: MEQP1_CodeAnalysis_EmptyBlock

<?php
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
switch ($foo) {
    // Empty switch statement body
}
switch ($foo) {
    case 'bar':
        break;
    default:
        break;
}
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
if ($foo) {
    // Just a comment
    //#Warn: MEQP1_CodeAnalysis_EmptyBlock
} elseif ($bar) {
    // Yet another comment
    //#Warn: MEQP1_CodeAnalysis_EmptyBlock
} else {
}
if ($foo) {
    $foo = 'bar';
} else if ($bar) {
    $bar = 'foo';
}
for ($i = 0; $i < 10; $i++) {
    //#Warn: MEQP1_CodeAnalysis_EmptyBlock
    for ($j = 0; $j < 10; $j++) {
        // Just a comment
    }
}
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
foreach ($foo as $bar) {}
foreach ($foo as $bar) {
    $bar *= 2;
}
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
do {
    // Just a comment
    // Just another comment
} while ($foo);
do {
    //#Warn: MEQP1_CodeAnalysis_EmptyBlock
    while ($bar) {
    }
} while (true);
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
while ($foo) { /* Comment in the same line */}
while ($foo) {
    //#Warn: MEQP1_CodeAnalysis_EmptyBlock
    try {
    } catch (Exception $e) {
        echo $e->getTraceAsString();
    }
}
try {
    throw Exception('Error...');
    //#Warn: MEQP1_CodeAnalysis_EmptyBlock
} catch (Exception $e) {}
try {
    throw Exception('Error...');
    //#Warn: MEQP1_CodeAnalysis_EmptyBlock
} catch (Exception $e) {
    // TODO: Handle this exception later :-)
}
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
if (true) {} elseif (false) {}
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
class EmptyBlockTestInc { /*Empty class block*/ }
class EmptyBlockTestIncTrue
{
    public $field;
}
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
abstract class EmptyBlockTestIncAbstract { /*Empty class block*/ }
abstract class EmptyBlockTestIncTrueAbstract
{
    public $field;
}
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
function emptyBlock () { /*Empty function block*/ }
function emptyBlockT () { return true; }
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
interface EmptyBlockInterface { /*Empty interface block*/ }
interface EmptyBlockInterfaceTrue {
    public function test();
}
//#Warn: MEQP1_CodeAnalysis_EmptyBlock
trait EmptyBlockTrait { /*Empty trait block*/ }
trait EmptyBlockTraitTrue {
    function test() {
        return true;
    }
}
