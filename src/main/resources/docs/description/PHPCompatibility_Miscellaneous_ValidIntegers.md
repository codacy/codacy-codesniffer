Check for valid integer types and values.
Checks:
- PHP 5. 4 introduced binary integers. 
- PHP 7. 0 removed tolerance for invalid octals.  These were truncated prior to PHP 7
  and give a parse error since PHP 7. 
- PHP 7. 0 removed support for recognizing hexadecimal numeric strings as numeric. 
  Type juggling and recognition was inconsistent prior to PHP 7.  As of PHP 7, they
  are no longer treated as numeric. 

PHP version 5. 4+