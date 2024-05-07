For loops that have only a second expression (the condition) should be converted to while loops.

Valid: A for loop is used with all three expressions.
```
for ($i = 0; $i < 10; $i++) {
    echo "{$i}\n";
}
```

Invalid: A for loop is used without a first or third expression.
```
for (;$test;) {
    $test = doSomething();
}
```
