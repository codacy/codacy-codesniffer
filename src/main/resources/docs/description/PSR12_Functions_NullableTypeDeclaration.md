In nullable type declarations there MUST NOT be a space between the question mark and the type.

Valid: no whitespace used.
```
public function functionName(
    ?string $arg1,
    ?int $arg2
): ?string {
}
```

Invalid: superfluous whitespace used.
```
public function functionName(
    ? string $arg1,
    ? int $arg2
): ? string {
}
```

Valid: no unexpected characters.
```
public function foo(?int $arg): ?string
{
}
```

Invalid: unexpected characters used.
```
public function bar(? /* comment */ int $arg): ?
    // nullable for a reason
    string
{
}
```
