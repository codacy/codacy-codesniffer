Variable names should be camelCased with the first letter lowercase.  Private and protected member variables should begin with an underscore

Valid: A multi-word variable uses camel casing.
```
$testNumber = 1;
```

Invalid: A multi-word variable uses underscores and initial capitalization.
```
$Test_Number = 1;
```

Valid: A private member variable begins with an underscore.
```
class Foo
{
    private $_bar;
}
```

Invalid: A private member variable does not begin with an underscore.
```
class Foo
{
    private $bar;
}
```
