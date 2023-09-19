Exactly one space is allowed after the NOT operator.

Valid: A NOT operator followed by one space.
```
if (! $someVar || ! $x instanceOf stdClass) {};
```

Invalid: A NOT operator not followed by whitespace or followed by too much whitespace.
```
if (!$someVar || !$x instanceOf stdClass) {};

if (!     $someVar || !
    $x instanceOf stdClass) {};
```
