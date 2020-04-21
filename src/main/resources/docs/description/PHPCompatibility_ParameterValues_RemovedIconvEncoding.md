Detect passing deprecated `$type` values to `iconv_get_encoding()`."The iconv and mbstring configuration options related to encoding have been
deprecated in favour of default_charset."

{@internal It is unclear which mbstring functions should be targetted, so for now,
only the iconv function is handled.}

PHP version 5.6