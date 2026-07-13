Language constructs that can be used without parentheses, must have a single space between the language construct keyword and its content.

Valid: Single space after language construct.
```
echo 'Hello, World!';
throw new Exception();
return $newLine;
```

Invalid: No space, more than one space or newline after language construct.
```
echo'Hello, World!';
throw   new   Exception();
return
$newLine;
```

Valid: Single space between yield and from.
```
function myGenerator() {
    yield from [1, 2, 3];
}
```

Invalid: More than one space or newline between yield and from.
```
function myGenerator() {
    yield  from [1, 2, 3];
    yield
    from [1, 2, 3];
}
```
