The opening PHP tag should be the first item in the file.

Valid: A file starting with an opening PHP tag.
```
<?php
echo 'Foo';
```

Invalid: A file with content before the opening PHP tag.
```
Beginning content
<?php
echo 'Foo';
```
