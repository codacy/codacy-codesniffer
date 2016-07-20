If a file is pure PHP code, it is preferable to omit the PHP closing tag at the end of the file.
This prevents accidental whitespace or new lines being added after the PHP closing tag, which may cause unwanted effects:

    <?php
    echo "Hello world";

    // ... more code

    echo "Last statement";

    // the script ends here with no PHP closing tag


[Source](http://www.php.net/manual/en/language.basic-syntax.phptags.php)
      