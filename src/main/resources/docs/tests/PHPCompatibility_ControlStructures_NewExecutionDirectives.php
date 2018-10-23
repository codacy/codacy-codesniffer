//#Patterns: PHPCompatibility_ControlStructures_NewExecutionDirectives

<?php

/*
 * The below directives are valid.
 */
declare(ticks=1);
declare ( ticks = 1 ) {} // Test with varying spacing.
declare(encoding='ISO-8859-1');
declare(strict_types=1) {
    $var = false;
}

/*
 * The below directives have invalid values.
 */
//#Warn: PHPCompatibility_ControlStructures_NewExecutionDirectives
declare(ticks=TICK_VALUE); // Invalid - only literals may be given as directive values.
declare(encoding='invalid'); // Invalid - not a valid encoding.
//#Warn: PHPCompatibility_ControlStructures_NewExecutionDirectives
declare(strict_types=false); // Invalid - only 1 is a valid value.


// Invalid directive name.
//#Warn: PHPCompatibility_ControlStructures_NewExecutionDirectives
declare(invalid=true);

// Incomplete directive.
declare(
