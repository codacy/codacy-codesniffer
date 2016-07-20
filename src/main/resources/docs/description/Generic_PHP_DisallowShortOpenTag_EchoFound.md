Prior to PHP 5.4.0, this short syntax only works with the `short_open_tag` configuration setting enabled. Instead of

    <?= $foo ...

You should write:

    <?php echo $foo ...

      