In a for loop declaration, there should be no space inside the brackets and there should be 0 spaces before and 1 space after semicolons.

Valid: Correct spacing used.
```
for ($i = 0; $i < 10; $i++) {
    echo $i;
}
```

Invalid: Invalid spacing used inside brackets.
```
for ( $i = 0; $i < 10; $i++ ) {
    echo $i;
}
```

Valid: Correct spacing used.
```
for ($i = 0; $i < 10; $i++) {
    echo $i;
}
```

Invalid: Invalid spacing used before semicolons.
```
for ($i = 0 ; $i < 10 ; $i++) {
    echo $i;
}
```

Valid: Correct spacing used.
```
for ($i = 0; $i < 10; $i++) {
    echo $i;
}
```

Invalid: Invalid spacing used after semicolons.
```
for ($i = 0;$i < 10;$i++) {
    echo $i;
}
```
