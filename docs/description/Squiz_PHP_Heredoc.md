Forbids the use of heredoc and nowdoc syntax.

Valid: Using standard strings or inline HTML.
```
$text = "some $text";



?>
some text
<?php
```

Invalid: Using heredoc or nowdoc syntax.
```
$text = <<<EOD
some $text
EOD;

echo <<<'EOD'
some text
EOD;
```
