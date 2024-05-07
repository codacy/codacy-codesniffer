## Exceptions: Reference Throwable Only

In PHP 7.0, a [`Throwable` interface was added](https://wiki.php.net/rfc/throwable-interface) that allows catching and handling errors in more cases than `Exception` previously allowed. So, if the catch statement contained `Exception` on PHP 5.x, it means it should probably be rewritten to reference `Throwable` on PHP 7.x. This sniff enforces that.
