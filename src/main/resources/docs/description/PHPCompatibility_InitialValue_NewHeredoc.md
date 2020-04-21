Detect a heredoc being used to set an initial value.As of PHP 5.3.0, it's possible to initialize static variables, class properties
and constants declared using the `const` keyword, using the Heredoc syntax.
And while not documented, heredoc initialization can now also be used for function param defaults.
See: https://3v4l.org/JVH8W

These heredocs (still) cannot contain variables. That's, however, outside the scope of the
PHPCompatibility library until such time as there is a PHP version in which this would be accepted.

PHP version 5.3