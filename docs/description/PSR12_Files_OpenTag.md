When the opening <?php tag is on the first line of the file, it must be on its own line with no other statements unless it is a file containing markup outside of PHP opening and closing tags.

Valid: Opening PHP tag on a line by itself.
```
<?php

echo 'hi';
```

Invalid: Opening PHP tag not on a line by itself.
```
<?php echo 'hi';
```

Valid: Opening PHP tag not on a line by itself, but has markup outside the closing PHP tag.
```
<?php declare(strict_types=1); ?>
<html>
<body>
    <?php
        // ... additional PHP code ...
    ?>
</body>
</html>
```

Invalid: Opening PHP tag not on a line by itself without any markup in the file.
```
<?php declare(strict_types=1); ?>
```
