If no interpolation or expressions are used in the body of a heredoc, nowdoc syntax should be used instead.

Valid: Using nowdoc syntax for a text string without any interpolation or expressions.
```
$nowdoc = <<<'EOD'
some text
EOD;
```

Invalid: Using heredoc syntax for a text string without any interpolation or expressions.
```
$heredoc = <<<EOD
some text
EOD;
```

Valid: Using heredoc syntax for a text string containing interpolation or expressions.
```
$heredoc = <<<"EOD"
some $text
EOD;
```

Invalid: Using heredoc syntax for a text string without any interpolation or expressions.
```
$heredoc = <<<"EOD"
some text
EOD;
```
