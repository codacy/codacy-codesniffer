## TypeHints: Nullable Type For Null Default Value

Checks whether the nullablity `?` symbol is present before each nullable and optional parameter (which are marked as `= null`):

```php
function foo(
	int $foo = null, // ? missing
	?int $bar = null // correct
) {

}
```