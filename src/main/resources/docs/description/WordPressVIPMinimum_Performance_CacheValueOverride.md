This sniff enforces checking the return value of a function before passing it to another one.
An example of a not checking return value is:

<code>
echo esc_url( wpcom_vip_get_term_link( $term ) );
</code>