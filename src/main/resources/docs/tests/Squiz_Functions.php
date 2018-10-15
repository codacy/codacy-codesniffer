<?php
//#Patterns: Squiz_Functions_LowercaseFunctionKeywords, Squiz_Functions_FunctionDeclarationArgumentSpacing

//#Err: Squiz_Functions_LowercaseFunctionKeywords
//#Warn: Squiz_Functions_FunctionDeclarationArgumentSpacing
FUNCTION myFunction($arg1,$arg2)
{
}

//#Err: Squiz_Functions_LowercaseFunctionKeywords
FuNcTiON myFunctionA    ($arg1, $arg2)
{
}

//#Err: Squiz_Functions_LowercaseFunctionKeywords
//#Warn: Squiz_Functions_FunctionDeclarationArgumentSpacing
functioN myFunctionB($arg1 = 1, $arg2 = 2)
{
}

//#Warn: Squiz_Functions_FunctionDeclarationArgumentSpacing
function myFunctionD($longArgument ,
    $longerArgument,
    $muchLongerArgument
) {
    // body
};

function myFunctionE(
    $longArgument,
    $longerArgument,
    $muchLongerArgument
) {
    // body
};

//#Warn: Squiz_Functions_FunctionDeclarationArgumentSpacing
function myFunctionF( $a ){
    // body
}


?>