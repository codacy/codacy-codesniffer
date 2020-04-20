# SlevomatCodingStandard_Variables_UnusedVariable

Looks for unused variables.

Sniff provides the following settings:

* `ignoreUnusedValuesWhenOnlyKeysAreUsedInForeach` (defaults to `false`): ignore unused `$value` in foreach when only `$key` is used

```php
foreach ($values as $key => $value) {
	echo $key;
}
```
