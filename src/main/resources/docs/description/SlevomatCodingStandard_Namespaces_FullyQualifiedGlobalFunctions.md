# SlevomatCodingStandard_Namespaces_FullyQualifiedGlobalFunctions

All references to global functions must be referenced via a fully qualified name.

Sniff provides the following settings:

* `include`: list of global functions that must be referenced via FQN. If not set all functions are considered.
* `includeSpecialFunctions`: include complete list of PHP internal functions that could be optimized when referenced via FQN.
* `exclude`: list of global functions that are allowed not to be referenced via FQN.
