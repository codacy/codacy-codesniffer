Detect the use of deprecated and/or removed PHP extensions.
This sniff examines function calls made and flags function calls to functions
prefixed with the dedicated prefix from a deprecated/removed native PHP extension. 

Suggests alternative extensions if available. 

As userland functions may be prefixed with a prefix also used by a native
PHP extension, the sniff offers the ability to whitelist specific functions
from being flagged by this sniff via a property in a custom ruleset
(since PHPCompatibility 7. 0. 2). 

{@internal This sniff is a candidate for removal once all functions from all
deprecated/removed extensions have been added to the RemovedFunctions sniff. }

PHP version All