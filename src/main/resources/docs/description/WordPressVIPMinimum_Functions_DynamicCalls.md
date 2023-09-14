This sniff enforces that certain functions are not
dynamically called.
An example:

<code>
  $func = 'func_num_args';
  $func();
</code>

See here: http://php. net/manual/en/migration71. incompatible. php

Note that this sniff does not catch all possible forms of dynamic
calling, only some.