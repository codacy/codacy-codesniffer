Improve code readability by inserting a blank line before all return statements in your CakePHP methods. This practice visually separates the function logic from the return statement.

```php
// Issue: No blank line before return
function fetchData() {
    $data = $this->repository->getData();
    return $data;
}

// Solution: Blank line added before return
function fetchData() {
    $data = $this->repository->getData();
    
    return $data;
}
```

<!-- Codacy PatPatBot reviewed: 2024-06-19T13:31:24.252Z -->
