Forbids mixing different binary boolean operators (&&, ||, and, or, xor) within a single expression without making precedence clear using parentheses.

Valid: Making precedence clear with parentheses.
```
$one = false;
$two = false;
$three = true;

$result = ($one && $two) || $three;
$result2 = $one && ($two || $three);
$result3 = ($one && !$two) xor $three;
$result4 = $one && (!$two xor $three);

if (
    ($result && !$result3)
| (!$result && $result3)
) {}
```

Invalid: Not using parentheses.
```
$one = false;
$two = false;
$three = true;

$result = $one && $two || $three;

$result3 = $one && !$two xor $three;


if (
    $result && !$result3
| !$result && $result3
) {}
```
