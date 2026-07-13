## Classes: Forbidden Public Property

Disallows using public properties.

This sniff provides the following setting:

*   `checkPromoted` (default: `false`): will check promoted properties too.
*   `allowReadonly` (default: `false`): will allow readonly properties.
*   `allowNonPublicSet` (default: `true`): will allow properties with `protected(set)` or `private(set)`.
