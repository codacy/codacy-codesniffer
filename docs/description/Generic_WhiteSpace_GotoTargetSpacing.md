There should be no space between the label for a goto target and the colon following it.

Valid: No space between the label and the colon.
```
goto labelA;
echo 'Foo';

labelA:
echo 'Bar';
```

Invalid: Whitespace between the label and the colon.
```
goto labelA;
echo 'Foo';

labelA   :
echo 'Bar';
```
