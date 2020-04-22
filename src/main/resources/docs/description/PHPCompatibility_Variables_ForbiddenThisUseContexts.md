Detect using `$this` in incompatible contexts.
"Whilst `$this` is considered a special variable in PHP, it lacked proper checks
 to ensure it wasn't used as a variable name or reassigned.  This has now been
 rectified to ensure that `$this` cannot be a user-defined variable, reassigned
 to a different value, or be globalised. "

This sniff only addresses those situations which did *not* throw an error prior
to PHP 7. 1, either at all or only in PHP 7. 0. 
In other words, the following situation, while mentioned in the RFC, will NOT
be sniffed for:
- Using $this as static variable.  (error _message_ change only). 

Also, the changes with relation to assigning `$this` dynamically can not be
sniffed for reliably, so are not covered by this sniff. 
- Disable ability to re-assign `$this` indirectly through `$$`. 
- Disable ability to re-assign `$this` indirectly through reference. 
- Disable ability to re-assign `$this` indirectly through `extract()` and `parse_str()`. 

Other changes not (yet) covered:
- `get_defined_vars()` always doesn't show value of variable `$this`. 
- Always show true `$this` value in magic method `__call()`. 
  {@internal This could possibly be covered.  Similar logic as "outside object context",
  but with function name check and supportsBelow('7. 0'). }

PHP version 7. 1