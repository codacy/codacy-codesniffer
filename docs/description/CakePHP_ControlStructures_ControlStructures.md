Improve code readability and maintainability by adhering to best practices for control structures in CakePHP. Refactor redundant or overly complex nested control structures.

```php
// Issue: Redundant nested if-else blocks
if ($condition) {
    if ($anotherCondition) {
        performAction();
    } else {
        handleElse();
    }
} else {
    handleElse();
}

// Solution: Simplified control structure
if ($condition && $anotherCondition) {
    performAction();
} else {
    handleElse();
}
```

<!-- Codacy PatPatBot reviewed: 2024-06-19T13:30:25.494Z -->
