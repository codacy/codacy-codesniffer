Detects using 0 and variable numeric arguments on `break` and `continue` statements.
This sniff checks for:
- Using `break` and/or `continue` with a variable as the numeric argument. 
- Using `break` and/or `continue` with a zero - 0 - as the numeric argument. 

PHP version 5. 4