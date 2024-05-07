Functions must have a non-empty doc comment.  The short description must be on the second line of the comment.  Each description must have one blank comment line before and after.  There must be one blank line before the tags in the comments.  There must be a tag for each of the parameters in the right order with the right variable names with a comment.  There must be a return tag.  Any throw tag must have an exception class.

Valid: A function doc comment is used.
```
/**
 * Short description here.
 *
 * @return void
 */
 function foo()
 {
 }
```

Invalid: No doc comment for the function.
```
function foo()
 {
 }
```

Valid: Short description is the second line of the comment.
```
/**
 * Short description here.
 *
 * @return void
 */
 function foo()
 {
 }
```

Invalid: An extra blank line before the short description.
```
/**
 * 
 * Short description here.
 *
 * @return void
 */
 function foo()
 {
 }
```

Valid: Exactly one blank line around descriptions.
```
/**
 * Short description here.
 * 
 * Long description here.
 * 
 * @return void
 */
 function foo()
 {
 }
```

Invalid: Extra blank lines around the descriptions.
```
/**
 * Short description here.
 * 
 * 
 * Long description here.
 * 
 * 
 * @return void
 */
 function foo()
 {
 }
```

Valid: Exactly one blank line before the tags.
```
/**
 * Short description here.
 *
 * Long description here.
 * 
 * @return void
 */
 function foo()
 {
 }
```

Invalid: Extra blank lines before the tags.
```
/**
 * Short description here.
 *
 * Long description here.
 * 
 * 
 * @return void
 */
 function foo()
 {
 }
```

Valid: Throws tag has an exception class.
```
/**
 * Short description here.
 *
 * @return void
 * @throws FooException
 */
 function foo()
 {
 }
```

Invalid: No exception class given for throws tag.
```
/**
 * Short description here.
 *
 * @return void
 * @throws
 */
 function foo()
 {
 }
```

Valid: Return tag present.
```
/**
 * Short description here.
 *
 * @return void
 */
 function foo()
 {
 }
```

Invalid: No return tag.
```
/**
 * Short description here.
 */
 function foo()
 {
 }
```

Valid: Param names are correct.
```
/**
 * Short description here.
 *
 * @param string $foo Foo parameter
 * @param string $bar Bar parameter
 * @return void
 */
 function foo($foo, $bar)
 {
 }
```

Invalid: Wrong parameter name doesn't match function signature.
```
/**
 * Short description here.
 *
 * @param string $foo Foo parameter
 * @param string $qux Bar parameter
 * @return void
 */
 function foo($foo, $bar)
 {
 }
```

Valid: Param names are ordered correctly.
```
/**
 * Short description here.
 *
 * @param string $foo Foo parameter
 * @param string $bar Bar parameter
 * @return void
 */
 function foo($foo, $bar)
 {
 }
```

Invalid: Wrong parameter order.
```
/**
 * Short description here.
 *
 * @param string $bar Bar parameter
 * @param string $foo Foo parameter
 * @return void
 */
 function foo($foo, $bar)
 {
 }
```
