All function and method names starting with double underscore are reserved by PHP.PHP version All

{@internal Extends an upstream sniff to benefit from the properties contained therein.
           The properties are lists of valid PHP magic function and method names, which
           should be ignored for the purposes of this sniff.
           As this sniff is not PHP version specific, we don't need access to the utility
           methods in the PHPCompatibility\Sniff, so extending the upstream sniff is fine.
           As the upstream sniff checks the same (and more, but we don't need the rest),
           the logic in this sniff is largely the same as used upstream.
           Extending the upstream sniff instead of including it via the ruleset, however,
           prevents hard to debug issues of errors not being reported from the upstream sniff
           if this library is used in combination with other rulesets.}