Detect use of the `ini_set()` function.
- Won't throw notices for "safe" ini directives as listed in the whitelist. 
- Throws errors for ini directives listed in the blacklist. 
- A warning will be thrown in all other cases.