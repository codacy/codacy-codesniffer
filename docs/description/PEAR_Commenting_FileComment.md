Files must have a non-empty doc comment.  The short description must be on the second line of the comment.  Each description must have one blank comment line before and after.  There must be one blank line before the tags in the comments.  There must be a category, package, author, license, and link tag.  There may only be one category, package, subpackage, license, version, since and deprecated tag.  The tags must be in the order category, package, subpackage, author, copyright, license, version, link, see, since, and deprecated.  The php version must be specified.

Valid: A file comment is used.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 *
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Invalid: No doc comment for the class.
```
<?php

```

Valid: Short description is the second line of the comment.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 *
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Invalid: An extra blank line before the short description.
```
<?php
/**
 * 
 * Short description here.
 *
 * PHP version 5
 *
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Valid: Exactly one blank line around descriptions.
```
<?php
/**
 * Short description here.
 * 
 * PHP version 5
 * 
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Invalid: Extra blank lines around the descriptions.
```
<?php
/**
 * Short description here.
 * 
 * 
 * PHP version 5
 * 
 * 
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Valid: Exactly one blank line before the tags.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 * 
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Invalid: Extra blank lines before the tags.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 * 
 * 
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Valid: All required tags are used.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 *
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Invalid: Missing an author tag.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 *
 * @category Foo
 * @package Foo_Helpers
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Valid: Tags that should only be used once are only used once.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 *
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Invalid: Multiple category tags.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 *
 * @category Foo
 * @category Bar
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Valid: PHP version specified.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 *
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Invalid: Category and package tags are swapped in order.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 *
 * @package Foo_Helpers
 * @category Foo
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Valid: Tags are in the correct order.
```
<?php
/**
 * Short description here.
 *
 * PHP version 5
 *
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```

Invalid: No php version specified.
```
<?php
/**
 * Short description here.
 *
 * @category Foo
 * @package Foo_Helpers
 * @author Marty McFly <mmcfly@example.com>
 * @copyright 2013-2014 Foo Inc.
 * @license MIT License
 * @link http://example.com
 */
```
