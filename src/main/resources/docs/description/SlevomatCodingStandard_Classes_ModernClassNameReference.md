## Classes: Modern Class Name Reference

Reports use of `__CLASS__`, `get_parent_class()`, `get_called_class()`, `get_class()` and `get_class($this)`.
Class names should be referenced via `::class` constant when possible.

Sniff provides the following settings:

* `enableOnObjects`: Enable `::class` on all objects. It's on by default if you're on PHP 8.0+