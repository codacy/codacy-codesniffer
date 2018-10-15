There should be a space between each element of a foreach loop and the as keyword should be lowercase.

Valid: Correct spacing used.
```
foreach ($foo as $bar => $baz) {
    echo $baz;
}
```

Invalid: Invalid spacing used.
```
foreach ( $foo  as  $bar=>$baz ) {
    echo $baz;
}
```

Valid: Lowercase as keyword.
```
foreach ($foo as $bar => $baz) {
    echo $baz;
}
```

Invalid: Uppercase as keyword.
```
foreach ($foo AS $bar => $baz) {
    echo $baz;
}
```
