The opening php tag should be the first item in the file.

Valid: A file starting with an opening php tag.
```
<?php
echo 'Foo';
```

Invalid: A file with content before the opening php tag.
```
Beginning content
<?php
echo 'Foo';
```
