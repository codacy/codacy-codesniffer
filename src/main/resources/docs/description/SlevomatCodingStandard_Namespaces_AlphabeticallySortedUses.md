## Namespaces: Alphabetically Sorted Uses

Sniff checks whether `use` declarations at the top of a file are alphabetically sorted. Follows natural sorting and takes edge cases with special symbols into consideration. The following code snippet is an example of correctly sorted uses:

```php
use LogableTrait;
use LogAware;
use LogFactory;
use LoggerInterface;
use LogLevel;
use LogStandard;
```

Sniff provides the following settings:


* `psr12Compatible` (defaults to `true`): sets the required order to `classes`, `functions` and `constants`. `false` sets the required order to `classes`, `constants` and `functions`.
* `caseSensitive`: compare namespaces case sensitively, which makes this order correct:

```php
use LogAware;
use LogFactory;
use LogLevel;
use LogStandard;
use LogableTrait;
use LoggerInterface;
```