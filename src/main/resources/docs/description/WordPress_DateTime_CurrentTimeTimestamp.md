Don't use current_time() to get a (timezone corrected) "timestamp".
Disallow using the current_time() function to get "timestamps" as it
doesn't produce a *real* timestamp, but a "WordPress timestamp", i. e. 
a Unix timestamp with current timezone offset, not a Unix timestamp ansich.