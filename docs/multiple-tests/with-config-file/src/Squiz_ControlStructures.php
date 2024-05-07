<?php
// do ... while
$i = 0;
do {
    echo $i;
} while ($i > 0);

do
{
    echo $i;
} while ($i > 0);

do
{
    echo $i;
}
while ($i > 0);

do { echo $i; } while ($i > 0);

do{
   echo $i;
}while($i > 0);


// while
while ($i < 1) {
    echo $i;
}

while($i < 1){
    echo $i;
}

while ($i < 1) { echo $i; }

// for
for ($i = 1; $i < 1; $i++) {
    echo $i;
}

for($i = 1; $i < 1; $i++){
    echo $i;
}

for ($i = 1; $i < 1; $i++) { echo $i; }

// foreach
foreach ($items as $item) {
    echo $item;
}

foreach($items as $item){
    echo $item;
}

for ($items as $item) { echo $item; }


// if
if ($i == 0) {
    $i = 1;
}

if($i == 0){
    $i = 1;
}

if ($i == 0) { $i = 1; }


// else
if ($i == 0) {
    $i = 1;
} else {
    $i = 0;
}

if ($i == 0) {
    $i = 1;
}else{
    $i = 0;
}

if ($i == 0) { $i = 1; } else { $i = 0; }

try {
    $code = 'this';
} catch (Exception $e) {
    // Caught!
}

try { $code = 'this'; } catch (Exception $e) {
    // Caught!
}

do { echo $i;
} while ($i > 0);
