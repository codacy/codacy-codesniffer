Empty PHP tags are not allowed.

Valid: There is at least one statement inside the PHP tag pair.
```
<?php echo 'Hello World'; ?>
<?= 'Hello World'; ?>
```

Invalid: There is no statement inside the PHP tag pair.
```
<?php ; ?>
<?=  ?>
```

Valid: There is no superfluous semicolon after a PHP statement.
```
function_call();
if (true) {
    echo 'Hello World';
}
```

Invalid: There are one or more superfluous semicolons after a PHP statement.
```
function_call();;;
if (true) {
    echo 'Hello World';
};
```
