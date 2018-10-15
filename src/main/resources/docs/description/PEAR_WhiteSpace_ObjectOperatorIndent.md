Chained object operators when spread out over multiple lines should be the first thing on the line and be indented by 1 level.

Valid: Object operator at the start of a new line.
```
$foo
    ->bar()
    ->baz();
```

Invalid: Object operator at the end of the line.
```
$foo->
    bar()->
    baz();
```

Valid: Object operator indented correctly.
```
$foo
    ->bar()
    ->baz();
```

Invalid: Object operator not indented correctly.
```
$foo
->bar()
->baz();
```
