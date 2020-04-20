# SlevomatCodingStandard_Exceptions_DeadCatch

This sniff finds unreachable catch blocks:

```php
try {
	doStuff();
} catch (\Throwable $e) {
	log($e);
} catch (\InvalidArgumentException $e) {
	// unreachable!
}
```

### Formatting - rules for consistent code looks
