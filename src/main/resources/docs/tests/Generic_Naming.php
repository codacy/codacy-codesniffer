<?php

//#Patterns: Generic_NamingConventions_UpperCaseConstantName, Generic_PHP_LowerCaseConstant, Generic_PHP_LowerCaseKeyword

//#Warn: Generic_PHP_LowerCaseKeyword
CLASS Something
{

    //#Err: Generic_NamingConventions_UpperCaseConstantName
    //#Warn: Generic_PHP_LowerCaseConstant
    const myTest = FALSE;

    //#Warn: Generic_PHP_LowerCaseKeyword
    CoNsT MYTEST = true;
}

?>