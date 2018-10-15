If a function throws any exceptions, they should be documented in a @throws tag.

Valid: @throws tag used.
```
/**
 * @throws Exception all the time
 * @return void
 */
function foo()
{
    throw new Exception('Danger!');
}
```

Invalid: No @throws tag used for throwing function.
```
/**
 * @return void
 */
function foo()
{
    throw new Exception('Danger!');
}
```
