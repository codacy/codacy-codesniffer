<?php

//#Patterns: WordPress_Security_ValidatedSanitizedInput : { "customSanitizingFunctions" : "do_something_with, bar_sanitized"}

do_something( $_POST ); // Ok.

//#Err: WordPress_Security_ValidatedSanitizedInput
//#Err: WordPress_Security_ValidatedSanitizedInput
//#Err: WordPress_Security_ValidatedSanitizedInput
do_something_with( $_POST['hello'] ); // Error for no validation, Error for no unslashing.

//#Err: WordPress_Security_ValidatedSanitizedInput
echo sanitize_text_field( wp_unslash( $_POST['foo1'] ) ); // Error for no validation.

if ( isset( $_POST['foo2'] ) ) {
	//#Err: WordPress_Security_ValidatedSanitizedInput
	bar_sanitized( wp_unslash( $_POST['foo2'] ) ); // No Error (customSanitizingFunctions)
	//#Err: WordPress_Security_ValidatedSanitizedInput
	bar_not_sanitized( wp_unslash( $_POST['foo2'] ) ); // Error for no sanitizing.
}
