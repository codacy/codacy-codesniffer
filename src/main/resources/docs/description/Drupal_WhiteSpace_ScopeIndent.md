Largely copied from
\PHP_CodeSniffer\Standards\Generic\Sniffs\WhiteSpace\ScopeIndentSniff,
modified to make the exact mode working with comments and multi line
statements.Checks that control structures are structured correctly, and their content
is indented correctly. This sniff will throw errors if tabs are used
for indentation rather than spaces.