Case and default keywords must be lowercase.

Valid: Keywords in lowercase.
```
switch ($foo) {
    case 'bar':
        break;
    default:
        break;
}
```

Invalid: Keywords not in lowercase.
```
switch ($foo) {
    CASE 'bar':
        break;
    Default:
        break;
}
```

Valid: Case statement followed by one space.
```
switch ($foo) {
    case 'bar':
        break;
}
```

Invalid: Case statement not followed by one space.
```
switch ($foo) {
    case'bar':
        break;
}
```

Valid: Colons not preceded by whitespace.
```
switch ($foo) {
    case 'bar':
        break;
    default:
        break;
}
```

Invalid: Colons preceded by whitespace.
```
switch ($foo) {
    case 'bar' :
        break;
    default :
        break;
}
```

Valid: Body starts on the next line.
```
switch ($foo) {
    case 'bar':
        break;
}
```

Invalid: Body on the same line as the case statement.
```
switch ($foo) {
    case 'bar': break;
}
```

Valid: Terminating statement on its own line.
```
switch ($foo) {
    case 'bar':
        echo $foo;
        return;
}
```

Invalid: Terminating statement not on its own line.
```
switch ($foo) {
    case 'bar':
        echo $foo; return;
}
```

Valid: Break statement indented correctly.
```
switch ($foo) {
    case 'bar':
        break;
}
```

Invalid: Break statement not indented four spaces.
```
switch ($foo) {
    case 'bar':
    break;
}
```

Valid: Using a colon for case and default statements.
```
switch ($foo) {
    case 'bar':
        break;
    default:
        break;
}
```

Invalid: Using a semi-colon or colon followed by braces.
```
switch ($foo) {
    case 'bar';
        break;
    default: {
        break;
    }
}
```

Valid: Comment marking intentional fall-through in a non-empty case body.
```
switch ($foo) {
    case 'bar':
        echo $foo;
        // no break
    default:
        break;
}
```

Invalid: No comment marking intentional fall-through in a non-empty case body.
```
switch ($foo) {
    case 'bar':
        echo $foo;
    default:
        break;
}
```
