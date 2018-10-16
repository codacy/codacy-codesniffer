<?php
//#Patterns: Squiz_Functions_LowercaseFunctionKeywords, Squiz_Functions_FunctionDeclarationArgumentSpacing

//#Info: Squiz_Functions_LowercaseFunctionKeywords
//#Info: Squiz_Functions_FunctionDeclarationArgumentSpacing
FUNCTION myFunction($arg1,$arg2)
{
}

//#Info: Squiz_Functions_LowercaseFunctionKeywords
FuNcTiON myFunctionA    ($arg1, $arg2)
{
}

//#Info: Squiz_Functions_LowercaseFunctionKeywords
//#Info: Squiz_Functions_FunctionDeclarationArgumentSpacing
//#Info: Squiz_Functions_FunctionDeclarationArgumentSpacing
//#Info: Squiz_Functions_FunctionDeclarationArgumentSpacing
//#Info: Squiz_Functions_FunctionDeclarationArgumentSpacing
functioN myFunctionB($arg1 = 1, $arg2 = 2)
{
}

//#Info: Squiz_Functions_FunctionDeclarationArgumentSpacing
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

//#Info: Squiz_Functions_FunctionDeclarationArgumentSpacing
//#Info: Squiz_Functions_FunctionDeclarationArgumentSpacing
function myFunctionF( $a ){
    // body
}


?>