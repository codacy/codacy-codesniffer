All PHP built-in functions should be lowercased when called.

Valid: Lowercase function call.
```
if (isset($foo)) {
    echo $foo;
}
```

Invalid: isset not called as lowercase.
```
if (isSet($foo)) {
    echo $foo;
}
```
