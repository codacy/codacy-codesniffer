PHP 7.4 now supports stand-alone PHP tags at the end of a file (without new line).> `<?php` at the end of the file (without trailing newline) will now be
> interpreted as an opening PHP tag. Previously it was interpreted either as
> `<? php` and resulted in a syntax error (with short_open_tag=1) or was
> interpreted as a literal `<?php` string (with short_open_tag=0).

{@internal Due to an issue with the Tokenizer, this sniff will not work correctly
           on PHP 5.3 in combination with PHPCS < 2.6.0 when short_open_tag is `On`.
           As this is causing "Undefined offset" notices, there is nothing we can
           do to work-around this.}

PHP version 7.4