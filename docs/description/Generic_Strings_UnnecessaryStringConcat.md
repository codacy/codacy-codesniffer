Strings should not be concatenated together.

Valid: A string can be concatenated with an expression.
```
echo '5 + 2 = ' . (5 + 2);
```

Invalid: Strings should not be concatenated together.
```
echo 'Hello' . ' ' . 'World';
```
