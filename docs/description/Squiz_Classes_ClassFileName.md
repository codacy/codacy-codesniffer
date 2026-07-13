The file name and the name of the class defined within the file must match exactly, including case.

Valid: Defined class name and filename match.
```
/* ./src/Foo.php contents: */

class Foo
{
}
```

Invalid: Filename does not match class name.
```
/* ./src/Foo.php contents: */

class MyFoo
{
}
```

Valid: Class name and filename use the same casing.
```
/* ./src/MyFoo.php contents: */

class MyFoo
{
}
```

Invalid: Filename case does not match class name case.
```
/* ./src/myFoo.php contents: */

class MyFoo
{
}
```
