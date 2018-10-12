Private member variable names should be prefixed with an underscore and public/protected variable names should not.

Valid: Proper member variable names.
```
class Foo
{
    public $publicVar;
    protected $protectedVar;
    private $_privateVar;
}
```

Invalid: underscores used on public/protected variables and not used on private variables.
```
class Foo
{
    public $_publicVar;
    protected $_protectedVar;
    private $privateVar;
}
```
