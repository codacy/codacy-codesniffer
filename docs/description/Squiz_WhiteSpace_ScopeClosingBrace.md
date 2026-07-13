Indentation of a closing brace must match the indentation of the line containing the opening brace.

Valid: Closing brace aligned with line containing opening brace.
```
function foo()
{
}

if (!class_exists('Foo')) {
    class Foo {
    }
}

<?php if ($something) { ?>
    <span>some output</span>
<?php } ?>
```

Invalid: Closing brace misaligned with line containing opening brace.
```
function foo()
{
 }

if (!class_exists('Foo')) {
    class Foo {
}
    }

<?php if ($something) { ?>
    <span>some output</span>
 <?php } ?>
```

Valid: Close brace on its own line.
```
enum Foo {
}
```

Invalid: Close brace on a line containing other code.
```
enum Foo {}
```
