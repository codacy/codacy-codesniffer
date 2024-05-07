All binary and ternary (but not unary) operators MUST be preceded and followed by at least one space. This includes all arithmetic, comparison, assignment, bitwise, logical (excluding ! which is unary), string concatenation, type operators, trait operators (insteadof and as), and the single pipe operator (e.g. ExceptionType1 | ExceptionType2 $e).

Valid: At least 1 space used.
```
if ($a === $b) {
    $foo = $bar ?? $a ?? $b;
} elseif ($a > $b) {
    $variable = $foo ? 'foo' : 'bar';
}
```

Invalid: No spacing used.
```
if ($a===$b) {
    $foo=$bar??$a??$b;
} elseif ($a>$b) {
    $variable=$foo?'foo':'bar';
}
```
