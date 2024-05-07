<?php

//#Patterns: WordPress_DB_PreparedSQL

//#Err: WordPress_DB_PreparedSQL
$wpdb->query( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '" . $_GET['title'] . "';" ); // Bad.
//#Err: WordPress_DB_PreparedSQL
$wpdb->query( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '{$_GET['title']}';" ); // Bad.
//#Err: WordPress_DB_PreparedSQL
$wpdb->query( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '$var';" ); // Bad.
$wpdb->query( "SELECT * FROM $wpdb->posts WHERE post_title LIKE 'Hello World!';" ); // Ok.
//#Err: WordPress_DB_PreparedSQL
$wpdb->query( $wpdb->prepare( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '{$_GET['title']}';" ) ); // Bad.
//#Err: WordPress_DB_PreparedSQL
$wpdb->query( $wpdb->prepare( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '$var';" ) ); // Bad.
$wpdb->query( $wpdb->prepare( "SELECT * FROM $wpdb->posts WHERE post_title LIKE %s;", $_GET['title'] ) ); // Ok.

//#Err: WordPress_DB_PreparedSQL
$wpdb->query( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '" . $escaped_var . "';" ); // Bad
//#Err: WordPress_DB_PreparedSQL
$wpdb->query( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '{$escaped_var}';" ); // Bad

$wpdb->query( $wpdb->prepare( "SELECT SUBSTRING( post_name, %d + 1 ) REGEXP '^[0-9]+$'", array( 123 ) ) ); // Ok.
$wpdb->query( $wpdb->prepare( "SELECT * FROM $wpdb->posts WHERE post_title = 'The \$_GET var can be evil.' AND ID = %s", array( 123 ) ) ); // Ok.
//#Err: WordPress_DB_PreparedSQL
$wpdb->query( $wpdb->prepare( "SELECT * FROM $wpdb->posts WHERE post_title = 'The $_GET[foo] var is evil.' AND ID = %s", array( 123 ) ) ); // Bad.
//#Err: WordPress_DB_PreparedSQL
$wpdb->query( $wpdb->prepare( "SELECT * FROM $wpdb->posts WHERE post_title = 'The \\$_GET[foo]// var is evil again.' AND ID = %s", array( 123 ) ) ); // Bad.
//#Err: WordPress_DB_PreparedSQL
$wpdb->query( $wpdb->prepare( "SELECT * FROM $wpdb->posts WHERE post_title = 'The \$_GET var can be evil, but $_GET[foo] var is evil.' AND ID = %s", array( 123 ) ) ); // Bad.

//#Err: WordPress_DB_PreparedSQL
$wpdb->query( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '" . foo() . "';" ); // Bad.
//#Err: WordPress_DB_PreparedSQL
$wpdb->query( $wpdb->prepare( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '" . foo() . "';" ) ); // Bad.

$wpdb->query( "SELECT * FROM " . $wpdb->posts . " WHERE post_title LIKE 'foo';" ); // Ok.

// All OK.
$all_post_meta = $wpdb->get_results( $wpdb->prepare( sprintf(
	'SELECT `post_id`, `meta_value` FROM `%s` WHERE `meta_key` = "sort_order" AND `post_id` IN (%s)',
	$wpdb->postmeta,
	implode( ',', array_fill( 0, count( $post_ids ), '%d' ) )
), $post_ids ) );

$wpdb->query( "SELECT * FROM $wpdb->posts WHERE post_title LIKE '" . esc_sql( $foo ) . "';" ); // Ok.
$wpdb->query( "SELECT * FROM $wpdb->posts WHERE ID = " . absint( $foo ) . ";" ); // Ok.
