# SlevomatCodingStandard_Classes_RequireSingleLineMethodSignature

Enforces method signature to be on a single line.

Sniff provides the following settings:

* `maxLineLength`: specifies max allowed line length. If signature would fit on it, it's enforced. Use 0 value to enforce for all methods, regardless of length.

* `includedMethodPatterns`: allows to configure which methods are included in sniff detection. This is an array of regular expressions (PCRE) with delimiters. You should not use this with `excludedMethodPatterns`, as it will not work properly.

* `excludedMethodPatterns`: allows to configure which methods are excluded from sniff detection. This is an array of regular expressions (PCRE) with delimiters. You should not use this with `includedMethodPatterns`, as it will not work properly.
