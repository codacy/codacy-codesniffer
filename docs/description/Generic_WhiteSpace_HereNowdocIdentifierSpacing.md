There should be no space between the <<< and the heredoc/nowdoc identifier string.

Valid: No space between the <<< and the identifier string.
```
$heredoc = <<<EOD
some text
EOD;
```

Invalid: Whitespace between the <<< and the identifier string.
```
$heredoc = <<<   END
some text
END;
```
