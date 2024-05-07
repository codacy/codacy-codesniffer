## Commenting: Useless Function Doc Comment

*   Checks for useless doc comments. If the native method declaration contains everything and the phpDoc does not add anything useful, it's reported as useless and can optionally be automatically removed with `phpcbf`.
*   Some phpDocs might still be useful even if they do not add any typehint information. They can contain textual descriptions of code elements and also some meaningful annotations like `@expectException` or `@dataProvider`.

Sniff provides the following settings:

*   `traversableTypeHints`: enforces which typehints must have specified contained type. E.g. if you set this to `\Doctrine\Common\Collections\Collection`, then `\Doctrine\Common\Collections\Collection` must always be supplied with the contained type: `\Doctrine\Common\Collections\Collection|Foo[]`.

This sniff can cause an error if you're overriding or implementing a parent method which does not have typehints. In such cases add `@phpcsSuppress SlevomatCodingStandard.TypeHints.ParameterTypeHint.MissingNativeTypeHint` annotation to the method to have this sniff skip it.
