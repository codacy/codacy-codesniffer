<?php
//#Patterns: Squiz_ControlStructures_ControlSignature

// do ... while
$i = 0;
do {
    echo $i;
} while ($i > 0);

//#Info: Squiz_ControlStructures_ControlSignature
do
{
    echo $i;
} while ($i > 0);

//#Info: Squiz_ControlStructures_ControlSignature
do
{
    echo $i;
//#Info: Squiz_ControlStructures_ControlSignature
}
while ($i > 0);

//#Info: Squiz_ControlStructures_ControlSignature
do { echo $i; } while ($i > 0);

//#Info: Squiz_ControlStructures_ControlSignature
do{
   echo $i;
//#Info: Squiz_ControlStructures_ControlSignature
//#Info: Squiz_ControlStructures_ControlSignature
}while($i > 0);


// while
while ($i < 1) {
    echo $i;
}

//#Info: Squiz_ControlStructures_ControlSignature
//#Info: Squiz_ControlStructures_ControlSignature
while($i < 1){
    echo $i;
}

//#Info: Squiz_ControlStructures_ControlSignature
while ($i < 1) { echo $i; }


// for
for ($i = 1; $i < 1; $i++) {
    echo $i;
}

//#Info: Squiz_ControlStructures_ControlSignature
//#Info: Squiz_ControlStructures_ControlSignature
for($i = 1; $i < 1; $i++){
    echo $i;
}

//#Info: Squiz_ControlStructures_ControlSignature
for ($i = 1; $i < 1; $i++) { echo $i; }


// foreach
foreach ($items as $item) {
    echo $item;
}

//#Info: Squiz_ControlStructures_ControlSignature
//#Info: Squiz_ControlStructures_ControlSignature
foreach($items as $item){
    echo $item;
}

//#Info: Squiz_ControlStructures_ControlSignature
for ($items as $item) { echo $item; }


// if
if ($i == 0) {
    $i = 1;
}

//#Info: Squiz_ControlStructures_ControlSignature
//#Info: Squiz_ControlStructures_ControlSignature
if($i == 0){
    $i = 1;
}

//#Info: Squiz_ControlStructures_ControlSignature
if ($i == 0) { $i = 1; }


// else
if ($i == 0) {
    $i = 1;
} else {
    $i = 0;
}

if ($i == 0) {
    $i = 1;
//#Info: Squiz_ControlStructures_ControlSignature
//#Info: Squiz_ControlStructures_ControlSignature
}else{
    $i = 0;
}

//#Info: Squiz_ControlStructures_ControlSignature
if ($i == 0) { $i = 1; } else { $i = 0; }

try {
    $code = 'this';
} catch (Exception $e) {
    // Caught!
}

//#Info: Squiz_ControlStructures_ControlSignature
try { $code = 'this'; } catch (Exception $e) {
    // Caught!
}

//#Info: Squiz_ControlStructures_ControlSignature
do { echo $i;
} while ($i > 0);

?>
