## Namespaces: Use Only Whitelisted Namespaces

Sniff disallows uses of other than configured namespaces.

Sniff provides the following settings:

*   `namespacesRequiredToUse`: namespaces in this array are the only ones allowed to be used. E.g. root project namespace.
*   `allowUseFromRootNamespace`: also allow using top-level namespace:

```php
use DateTimeImmutable;
```
