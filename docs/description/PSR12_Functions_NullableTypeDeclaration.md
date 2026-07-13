In nullable type declarations there MUST NOT be a space between the question mark and the type.

Valid: No whitespace used.
```
public function functionName(
    ?string $arg1,
    ?int $arg2
): ?string {
}
```

Invalid: Superfluous whitespace used.
```
public function functionName(
    ? string $arg1,
    ? int $arg2
): ? string {
}
```

Valid: No unexpected characters.
```
public function foo(?int $arg): ?string
{
}
```

Invalid: Unexpected characters used.
```
public function bar(? /* comment */ int $arg): ?
    // nullable for a reason
    string
{
}
```
