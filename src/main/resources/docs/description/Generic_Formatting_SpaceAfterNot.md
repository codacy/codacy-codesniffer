Exactly one space is allowed after the NOT operator.

Valid: A NOT operator followed by one space.
```
if (! $someVar || ! $x instanceOf stdClass) {};
```

Invalid: A NOT operator not followed by whitespace.
```
if (!$someVar || !$x instanceOf stdClass) {};
```

Invalid: A NOT operator followed by a new line or more than one space.
```
if (!     $someVar || !
    $x instanceOf stdClass) {};
```
