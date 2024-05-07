## Commenting: Forbidden Annotations

Reports forbidden annotations. No annotations are forbidden by default, the configuration is completely up to the user. It's recommended to forbid obsolete and inappropriate annotations like:

*   `@author`, `@created`, `@version`: we have version control systems.
*   `@package`: we have namespaces.
*   `@copyright`, `@license`: it's not necessary to repeat licensing information in each file.
*   `@throws`: it's not possible to enforce this annotation and the information can become outdated.

Sniff provides the following settings:

*   `forbiddenAnnotations`: allows to configure which annotations are forbidden to be used.
