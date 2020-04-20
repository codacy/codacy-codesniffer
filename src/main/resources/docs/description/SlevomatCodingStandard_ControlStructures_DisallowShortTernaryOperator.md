# SlevomatCodingStandard_ControlStructures_DisallowShortTernaryOperator

Disallows short ternary operator `?:`.

Sniff provides the following settings:

* `fixable`: the sniff is fixable by default, however in strict code it makes sense to forbid this weakly typed form of ternary altogether, you can disable fixability with this option.
