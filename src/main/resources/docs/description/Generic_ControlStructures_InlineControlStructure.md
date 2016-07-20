The body of each control structure must be enclosed by braces.
Inline control statements may cause bugs when new lines are added to the body.

Instead of

    if ($something) echo 'hello';

You should write:

    if ($something) {
      echo 'hello';
    }
      