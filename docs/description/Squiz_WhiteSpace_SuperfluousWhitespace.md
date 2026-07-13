There should be no superfluous whitespace at the start of a file.

Valid: No whitespace preceding first content in file.
```
<?php
echo 'opening PHP tag at start of file';
```

Invalid: Whitespace used before content in file.
```
        
<?php
echo 'whitespace before opening PHP tag';
```

Valid: No whitespace found at end of line.
```
echo 'semicolon followed by new line char';
```

Invalid: Whitespace found at end of line.
```
echo 'trailing spaces after semicolon';   
```

Valid: Functions do not contain multiple empty lines in a row.
```
function myFunction()
{
    echo 'code here';

    echo 'code here';
}
```

Invalid: Functions contain multiple empty lines in a row.
```
function myFunction()
{
    echo 'code here';
    

    echo 'code here';
}
```

Valid: A single new line appears after the last content in the file.
```
function myFunction()
{
    echo 'Closing PHP tag, then';
    echo 'Single new line char, then EOF';
}

?>

```

Invalid: Multiple new lines appear after the last content in the file.
```
function myFunction()
{
    echo 'Closing PHP tag, then';
    echo 'Multiple new line chars, then EOF';
}

?>


```
