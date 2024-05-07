Incrementers in nested loops should use different variable names.

Valid: Two different variables being used to increment.
```
for ($i = 0; $i < 10; $i++) {
    for ($j = 0; $j < 10; $j++) {
    }
}
```

Invalid: Inner incrementer is the same variable name as the outer one.
```
for ($i = 0; $i < 10; $i++) {
    for ($j = 0; $j < 10; $i++) {
    }
}
```
