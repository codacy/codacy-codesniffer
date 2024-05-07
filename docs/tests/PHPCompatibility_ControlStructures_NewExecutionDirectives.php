<?php
//#Patterns: PHPCompatibility_ControlStructures_NewExecutionDirectives

/*
 * The below directives are valid.
 */
declare(ticks=1);
declare ( ticks = 1 ) {} // Test with varying spacing.
declare(encoding='ISO-8859-1');
declare(strict_types=1) {
    $var = false;
}

// Incomplete directive.
declare(
