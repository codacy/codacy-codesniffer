<?php
//#Patterns: PHPCompatibility_ParameterValues_RemovedMbstringModifiers

// These should all be ignored as we're passing in a variable.
mb_ereg_replace( $pattern, $replace, $subject, $options );
mb_eregi_replace( $pattern, $replace, $subject, $options );
mb_regex_set_options( $options );

// These should be ignored as they contain valid options.
mb_ereg_replace( $pattern, $replace, $subject, 'msi' );
mb_eregi_replace( $pattern, $replace, $subject, 'sim' );
mb_regex_set_options( 'ims' );

// Interpolated strings: These should NOT be flagged.
mb_ereg_replace( $pattern, $replace, $subject, "$e" );
mb_eregi_replace( $pattern, $replace, $subject, "s$eim" );
mb_regex_set_options( 'im' . "$se" );
