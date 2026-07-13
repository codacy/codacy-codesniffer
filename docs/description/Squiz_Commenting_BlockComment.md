A block comment is a multi-line comment delimited by an opener "/*" and a closer "*/" which are each on their own line with the comment text in between.

Valid: Uses a valid opener and closer.
```
/*
    A block comment.
*/
```

Invalid: Uses /** **/.
```
/**
    A block comment.
**/
```

Valid: Uses a valid opener and closer.
```
/*
 * A block comment
 * with multiple lines.
 */
```

Invalid: Uses multiple // or #.
```
// A block comment
// with multiple lines.

# A block comment
# with multiple lines.
```

Valid: Multi-line block comment.
```
/*
    A block comment.
*/
```

Invalid: Single line block comment.
```
/* A block comment. */
```

Valid: A block comment with contents.
```
/*
    A block comment.
*/
```

Invalid: An empty block comment.
```
/*

*/
```

Valid: Text starts on a new line.
```
/*
    A block comment.
*/
```

Invalid: Text starts on the same line.
```
/* A block comment.
*/
```

Valid: Indented by at least 4 spaces.
```
/*
    A block comment
      with multiple lines.
    And a second paragraph.
*/
```

Invalid: Indented by less than 4 spaces.
```
/*
 A block comment
  with
   multiple lines.
*/
```

Valid: Asterisks are aligned.
```
/*
 * A block comment
 * with
 * multiple lines.
 */
```

Invalid: Asterisks are not aligned.
```
/*
 * A block comment
  * with
 * multiple lines.
*/
```

Valid: Starts with a capital letter.
```
/*
    A block comment.
*/
```

Invalid: Does not start with a capital letter.
```
/*
    a block comment.
*/
```

Valid: Closer is on a new line.
```
/*
    A block comment.
*/
```

Invalid: Closer is not on a new line.
```
/*
    A block comment. */
```

Valid: The closer's asterisk is aligned with other asterisks.
```
/*
 * A block comment
 */
```

Invalid: The closer's asterisk is not aligned with other asterisks.
```
/*
 * A block comment.
*/
```

Valid: The closer's asterisk is aligned with the opener's slash.
```
/*
    A block comment.
*/
```

Invalid: The closer's asterisk is not aligned with the opener's slash.
```
/*
    A block comment.
 */
```

Valid: An empty line after the comment.
```
/*
    A block comment.
*/

echo 'Content';
```

Invalid: No empty line after the comment.
```
/*
    A block comment.
*/
echo 'Content';
```

Valid: No blank line after an open tag.
```
<?php
/*
 * A block comment
 * with
 * multiple lines.
 */
```

Invalid: A blank line after an open tag.
```
<?php

/*
 * A block comment
 * with
 * multiple lines.
 */
```
