Warn on line indentation ending with spaces for precision alignment.
WP demands tabs for indentation.  In rare cases, spaces for precision alignment can be
intentional and acceptable, but more often than not, this is a typo. 

The `Generic. WhiteSpace. DisallowSpaceIndent` sniff already checks for space indentation
and auto-fixes to tabs. 

This sniff only checks for precision alignments which can not be corrected by the
`Generic. WhiteSpace. DisallowSpaceIndent` sniff. 

As this may be intentional, this sniff explicitly does *NOT* contain a fixer.