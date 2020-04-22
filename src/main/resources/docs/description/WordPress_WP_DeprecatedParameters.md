Check for usage of deprecated parameters in WP functions and suggest alternative based on the parameter passed.
This sniff will throw an error when usage of deprecated parameters is
detected if the parameter was deprecated before the minimum supported
WP version; a warning otherwise. 
By default, it is set to presume that a project will support the current
WP version and up to three releases before.