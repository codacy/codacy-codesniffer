Detect usage of flexible heredoc/nowdoc and related cross-version incompatibilities.As of PHP 7.3:
- The body and the closing marker of a heredoc/nowdoc can be indented;
- The closing marker no longer needs to be on a line by itself;
- The heredoc/nowdoc body may no longer contain the closing marker at the
  start of any of its lines.

PHP version 7.3