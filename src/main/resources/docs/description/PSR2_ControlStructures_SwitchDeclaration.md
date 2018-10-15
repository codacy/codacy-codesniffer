Case statements should be indented 4 spaces from the switch keyword.  It should also be followed by a space.  Colons in switch declarations should not be preceded by whitespace.  Break statements should be indented 4 more spaces from the case statement.  There must be a comment when falling through from one case into the next.

Valid: Case statement indented correctly.
```
switch ($foo) {
    case 'bar':
        break;
}
```

Invalid: Case statement not indented 4 spaces.
```
switch ($foo) {
case 'bar':
    break;
}
```

Valid: Case statement followed by 1 space.
```
switch ($foo) {
    case 'bar':
        break;
}
```

Invalid: Case statement not followed by 1 space.
```
switch ($foo) {
    case'bar':
        break;
}
```

Valid: Colons not prefixed by whitespace.
```
switch ($foo) {
    case 'bar':
        break;
    default:
        break;
}
```

Invalid: Colons prefixed by whitespace.
```
switch ($foo) {
    case 'bar' :
        break;
    default :
        break;
}
```

Valid: Break statement indented correctly.
```
switch ($foo) {
    case 'bar':
        break;
}
```

Invalid: Break statement not indented 4 spaces.
```
switch ($foo) {
    case 'bar':
    break;
}
```

Valid: Comment marking intentional fall-through.
```
switch ($foo) {
    case 'bar':
    // no break
    default:
        break;
}
```

Invalid: No comment marking intentional fall-through.
```
switch ($foo) {
    case 'bar':
    default:
        break;
}
```
