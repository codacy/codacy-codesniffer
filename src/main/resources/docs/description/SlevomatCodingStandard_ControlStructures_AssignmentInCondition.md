# SlevomatCodingStandard_ControlStructures_AssignmentInCondition

Disallows assignments in `if`, `elseif` and `do-while` loop conditions:

```php
if ($file = findFile($path)) {

}
```

Assignment in `while` loop condition is specifically allowed because it's commonly used.

This is a great addition to already existing `SlevomatCodingStandard.ControlStructures.DisallowYodaComparison` because it prevents the danger of assigning something by mistake instead of using comparison operator like `===`.
