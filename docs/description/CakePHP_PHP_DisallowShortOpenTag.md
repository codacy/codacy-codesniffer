To ensure code portability and readability, always use full PHP tags (<?php ... ?>) instead of short tags (<? ... ?>). This practice avoids configuration dependencies and aligns with recommended coding standards.

```php
// Issue: Using short open tag
<? echo 'Hello, World!'; ?>

// Solution: Using full open tag
<?php echo 'Hello, World!'; ?>
```

<!-- Codacy PatPatBot reviewed: 2024-06-19T13:34:46.252Z -->
