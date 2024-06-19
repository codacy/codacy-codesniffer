Ensure consistent white space by avoiding the use of both tabs and spaces for indentation. Regularize the use of a single style (either tabs or spaces) as per your project's guidelines for improved readability and maintainability.

```php
// Issue: Mixed tabs and spaces for indentation
function example() {
↹$value = 42; // Tab indentation
    $value = 42; // Space indentation
}

// Solution: Consistent use of spaces for indentation
function example() {
    $value = 42; // Space indentation
    $value = 42; // Space indentation
}
```

<!-- Codacy PatPatBot reviewed: 2024-06-19T13:37:20.013Z -->
