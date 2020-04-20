# SlevomatCodingStandard_Arrays_TrailingArrayComma

Commas after last element in an array make adding a new element easier and result in a cleaner versioning diff.

This sniff enforces trailing commas in multi-line arrays and requires short array syntax `[]`.

Sniff provides the following settings:

* `enableAfterHeredoc`: enables/disables trailing commas after HEREDOC/NOWDOC, default based on PHP version.
