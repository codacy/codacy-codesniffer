## Namespaces: Reference Used Names Only

Sniff provides the following settings:

*   `searchAnnotations` (defaults to `false`): enables searching for mentions in annotations.
*   `namespacesRequiredToUse`: if not set, all namespaces are required to be used. When set, only mentioned namespaces are required to be used. Useful in tandem with UseOnlyWhitelistedNamespaces sniff.
*   `allowFullyQualifiedExceptions`, `specialExceptionNames` & `ignoredNames`: allows fully qualified exceptions. Useful in tandem with FullyQualifiedExceptions sniff.
*   `allowFullyQualifiedNameForCollidingClasses`: allow fully qualified name for a class with a colliding use statement.
*   `allowFullyQualifiedNameForCollidingFunctions`: allow fully qualified name for a function with a colliding use statement.
*   `allowFullyQualifiedNameForCollidingConstants`: allow fully qualified name for a constant with a colliding use statement.
*   `allowFullyQualifiedGlobalClasses`: allows using fully qualified classes from global space (i.e. `\DateTimeImmutable`).
*   `allowFullyQualifiedGlobalFunctions`: allows using fully qualified functions from global space (i.e. `\phpversion()`).
*   `allowFullyQualifiedGlobalConstants`: allows using fully qualified constants from global space (i.e. `\PHP_VERSION`).
*   `allowFallbackGlobalFunctions`: allows using global functions via fallback name without `use` (i.e. `phpversion()`).
*   `allowFallbackGlobalConstants`: allows using global constants via fallback name without `use` (i.e. `PHP_VERSION`).
*   `allowPartialUses`: allows using and referencing whole namespaces.
