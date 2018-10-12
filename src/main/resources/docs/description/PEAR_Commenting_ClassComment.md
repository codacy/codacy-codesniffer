Classes and interfaces must have a non-empty doc comment.  The short description must be on the second line of the comment.  Each description must have one blank comment line before and after.  There must be one blank line before the tags in the comments.  A @version tag must be in Release: package_version format.

Valid: A doc comment for the class.
```
/**
 * The Foo class.
 */
class Foo
{
}
```

Invalid: No doc comment for the class.
```
class Foo
{
}
```

Valid: A doc comment for the class.
```
/**
 * The Foo class.
 */
class Foo
{
}
```

Invalid: Invalid comment type for the class.
```
// The Foo class.
class Foo
{
}
```

Valid: A doc comment for the class.
```
/**
 * The Foo class.
 */
class Foo
{
}
```

Invalid: The blank line after the comment makes it appear as a file comment, not a class comment.
```
/**
 * The Foo class.
 */

class Foo
{
}
```

Valid: Short description is the second line of the comment.
```
/**
 * The Foo class.
 */
class Foo
{
}
```

Invalid: An extra blank line before the short description.
```
/**
 *
 * The Foo class.
 */
class Foo
{
}
```

Valid: Exactly one blank line around descriptions.
```
/**
 * The Foo class.
 * 
 * A helper for the Bar class.
 * 
 * @see Bar
 */
class Foo
{
}
```

Invalid: Extra blank lines around the descriptions.
```
/**
 * The Foo class.
 * 
 * 
 * A helper for the Bar class.
 * 
 * 
 * @see Bar
 */
class Foo
{
}
```

Valid: Exactly one blank line before the tags.
```
/**
 * The Foo class.
 * 
 * @see Bar
 */
class Foo
{
}
```

Invalid: Extra blank lines before the tags.
```
/**
 * The Foo class.
 * 
 * 
 * @see Bar
 */
class Foo
{
}
```

Valid: Version tag is in the correct format.
```
/**
 * The Foo class.
 *
 * @version Release: 1.0
 */
class Foo
{
}
```

Invalid: No Release: text.
```
/**
 * The Foo class.
 *
 * @version 1.0
 */
class Foo
{
}
```
