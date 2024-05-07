There should be no space between the spread operator and the variable/function call it applies to.

Valid: No space between the spread operator and the variable/function call it applies to.
```
function foo(&...$spread) {
    bar(...$spread);

    bar(
        [...$foo],
        ...array_values($keyedArray)
    );
}
```

Invalid: space found between the spread operator and the variable/function call it applies to.
```
function bar(... $spread) {
    bar(...
        $spread
    );

    bar(
        [...  $foo ],.../*comment*/array_values($keyedArray)
    );
}
```
