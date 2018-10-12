Files that contain php code should only have php code and should not have any "inline html".

Valid: A php file with only php code in it.
```
<?php
$foo = 'bar';
echo $foo . 'baz';
```

Invalid: A php file with html in it outside of the php tags.
```
some string here
<?php
$foo = 'bar';
echo $foo . 'baz';
```
