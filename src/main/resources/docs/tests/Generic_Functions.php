//#Patterns: Generic_Functions_FunctionCallArgumentSpacing

<?php

$result = myFunction();
$result = myFunction($arg1, $arg2);
//#Info: Generic_Functions_FunctionCallArgumentSpacing
$result = myFunction($arg1,$arg2);
//#Info: Generic_Functions_FunctionCallArgumentSpacing
$result = myFunction($arg1 , $arg2);
//#Info: Generic_Functions_FunctionCallArgumentSpacing
//#Info: Generic_Functions_FunctionCallArgumentSpacing
$result = myFunction($arg1 ,  $arg2);
//#Info: Generic_Functions_FunctionCallArgumentSpacing
$result = myFunction($arg1, $arg2, $arg3,$arg4, $arg5);
$result = myFunction($arg1, $arg2, $arg3, $arg4, $arg5);
$result = myFunction($arg1, $arg2 = array());
//#Info: Generic_Functions_FunctionCallArgumentSpacing
//#Info: Generic_Functions_FunctionCallArgumentSpacing
$result = myFunction($arg1 , $arg2 =array());
//#Info: Generic_Functions_FunctionCallArgumentSpacing
//#Info: Generic_Functions_FunctionCallArgumentSpacing
$result = myFunction($arg1 , $arg2= array());
//#Info: Generic_Functions_FunctionCallArgumentSpacing
//#Info: Generic_Functions_FunctionCallArgumentSpacing
//#Info: Generic_Functions_FunctionCallArgumentSpacing
$result = myFunction($arg1 , $arg2=array());


// Function definitions are ignored
function myFunction($arg1,$arg2)
{
}

function myFunction    ($arg1,$arg2)
{
}

function myFunction($arg1=1,$arg2=2)
{
}


function myFunction($arg1 = 1,$arg2 = 2)
{
}

?>