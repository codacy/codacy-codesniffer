Detect code affected by the changed list assignment order in PHP 7.0+.The `list()` construct no longer assigns variables in reverse order.
This affects all list constructs where non-unique variables are used.

PHP version 7.0