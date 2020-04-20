# SlevomatCodingStandard_Classes_RequireMultiLineMethodSignature

Enforces method signature to be splitted to more lines so each parameter is on its own line.

Sniff provides the following settings:

* `minLineLength`: specifies min line length to enforce signature to be splitted. Use 0 value to enforce for all methods, regardless of length.

* `includedMethodPatterns`: allows to configure which methods are included in sniff detection. This is an array of regular expressions (PCRE) with delimiters. You should not use this with `excludedMethodPatterns`, as it will not work properly.

* `excludedMethodPatterns`: allows to configure which methods are excluded from sniff detection. This is an array of regular expressions (PCRE) with delimiters. You should not use this with `includedMethodPatterns`, as it will not work properly.
