This sniff validates a proper usage of pre_get_posts action callback.
It looks for cases when the WP_Query object is being modified without checking for WP_Query::is_main_query().