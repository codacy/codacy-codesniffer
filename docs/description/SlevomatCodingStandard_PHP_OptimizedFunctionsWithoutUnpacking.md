## PHP: Optimized Functions Without Unpacking

PHP optimizes some internal functions into special opcodes on VM level. Such optimization results in much faster execution compared to calling standard functions. This only works when these functions are not invoked with argument unpacking (`...`).

The list of these functions varies across PHP versions, but is the same as functions that must be referenced by their global name (either by `\ ` prefix or using `use function`), not a fallback name inside namespaced code.
