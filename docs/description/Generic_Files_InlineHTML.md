Files that contain PHP code should only have PHP code and should not have any "inline html".

Valid: A PHP file with only PHP code in it.
```
<?php
$foo = 'bar';
echo $foo . 'baz';
```

Invalid: A PHP file with html in it outside of the PHP tags.
```
some string here
<?php
$foo = 'bar';
echo $foo . 'baz';
```
