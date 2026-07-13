Enforces rules related to the formatting of DocBlocks ("Doc Comments") in PHP code.

    DocBlocks are a special type of comment that can provide information about a structural element. In the context of DocBlocks, the following are considered structural elements:
    class, interface, trait, enum, function, property, constant, variable declarations and require/include[_once] statements.

    DocBlocks start with a `/**` marker and end on `*/`. This sniff will check the formatting of all DocBlocks, independently of whether or not they are attached to a structural element.

Valid: DocBlock with some content.
```
/**
 * Some content.
 */
```

Invalid: Empty DocBlock.
```
/**
 * 
 */
```

Valid: The opening and closing DocBlock tags have to be on a line by themselves.
```
/**
 * Short description.
 */
```

Invalid: The opening and closing DocBlock tags are not on a line by themselves.
```
/** Short description. */
```

Valid: DocBlock with a short description on the first line.
```
/**
 * Short description.
 */
```

Invalid: DocBlock without a short description or short description not on the first line.
```
/**
 * @return int
 */

/**
 *
 * Short description.
 */
```

Valid: Both the short and long description start with a capital letter.
```
/**
 * Short description.
 *
 * Long description.
 */
```

Invalid: Neither short nor long description starts with a capital letter.
```
/**
 * short description.
 *
 * long description.
 */
```

Valid: One blank line separating the short description, the long description and tag groups.
```
/**
 * Short description.
 *
 * Long description.
 *
 * @param int $foo
 */
```

Invalid: More than one or no blank line separating the short description, the long description and tag groups.
```
/**
 * Short description.
 *
 *

 * Long description.
 * @param int $foo
 */
```

Valid: Parameter tags grouped together.
```
/**
 * Short description.
 *
 * @param int $foo
 * @param string $bar
 */
```

Invalid: Parameter tags not grouped together.
```
/**
 * Short description.
 *
 * @param int $foo
 *
 * @param string $bar
 */
```

Valid: Parameter tags are not grouped together with other tags.
```
/**
 * Short description.
 *
 * @param int $foo
 *
 * @since      3.4.8
 * @deprecated 6.0.0
 */
```

Invalid: Parameter tags grouped together with other tags.
```
/**
 * Short description.
 *
 * @param      int $foo
 * @since      3.4.8
 * @deprecated 6.0.0
 */
```

Valid: Tag values for different tags in the same tag group are aligned with each other.
```
/**
 * Short description.
 *
 * @since      0.5.0
 * @deprecated 1.0.0
 */
```

Invalid: Tag values for different tags in the same tag group are not aligned with each other.
```
/**
 * Short description.
 *
 * @since 0.5.0
 * @deprecated 1.0.0
 */
```

Valid: Parameter tags are defined first.
```
/**
 * Short description.
 *
 * @param string $foo
 *
 * @return void
 */
```

Invalid: Parameter tags are not defined first.
```
/**
 * Short description.
 *
 * @return void
 *
 * @param string $bar
 */
```

Valid: No additional blank lines before the closing DocBlock tag.
```
/**
 * Short description.
 */
```

Invalid: Additional blank lines before the closing DocBlock tag.
```
/**
 * Short description.
 *
 */
```
