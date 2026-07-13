There should be exactly one blank line before the first property (member variable).

Valid: One blank line before the first property.
```
class MyClass
{

    protected $var1 = 'value';
}
```

Invalid: Incorrect number of blank lines before the first property.
```
class MyClass
{
    protected $var1 = 'value';
}
```

Valid: One blank line between each property.
```
trait MyTrait {

    public $var1 = 'value';

    public $var2 = 'value2';

    public $var3 = 'value3';
}
```

Invalid: Incorrect number of blank lines between each property.
```
trait MyTrait {

    public $var1 = 'value';


    public $var2 = 'value2';
    public $var3 = 'value3';
}
```

Valid: No blank lines between DocBlock and its property.
```
$anon = new class {

    /**
     * The actions that this class can perform.
     *
     * @var array
     */
    public $actions = array();
};
```

Invalid: Blank line(s) between DocBlock and its property.
```
$anon = new class {

    /**
     * The actions that this class can perform.
     *
     * @var array
     */

    public $actions = array();
};
```
