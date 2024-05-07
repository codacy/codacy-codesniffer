For loops should not call functions inside the test for the loop when they can be computed beforehand.

Valid: A for loop that determines its end condition before the loop starts.
```
$end = count($foo);
for ($i = 0; $i < $end; $i++) {
    echo $foo[$i]."\n";
}
```

Invalid: A for loop that unnecessarily computes the same value on every iteration.
```
for ($i = 0; $i < count($foo); $i++) {
    echo $foo[$i]."\n";
}
```
