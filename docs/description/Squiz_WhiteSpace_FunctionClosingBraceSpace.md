There should be a single blank line before the function closing brace.

Valid: A single blank line before the function closing brace.
```
function outer() {

}
```

Invalid: No blank line before the function closing brace.
```
function outer() {}
```

Valid: Nested function closing brace is on a new line.
```
function outer() {
    function inner() {
    }

}
```

Invalid: Nested function closing brace on the same line.
```
function outer() {
    function inner() {}

}
```

Valid: No blank line before the closing brace of a nested function.
```
function outer() {
    function inner() {
    }

}
```

Invalid: A single blank line before the closing brace of a nested function.
```
function outer() {
    function inner() {

    }

}
```
