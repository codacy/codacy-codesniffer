Checks against empty statements.- Check against two semi-colons with no executable code in between.
- Check against an empty PHP open - close tag combination.

{@internal This check should at some point in the future be pulled upstream and probably
           merged into the upstream `Generic.CodeAnalysis.EmptyStatement` sniff.
           This will need to wait until the WPCS minimum requirements have gone up
           beyond PHPCS 3.x though as it is not likely that new features will be accepted
           still for the PHPCS 2.x branch.}}