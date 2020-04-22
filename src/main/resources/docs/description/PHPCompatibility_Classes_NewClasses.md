Detect use of new PHP native classes.
The sniff analyses the following constructs to find usage of new classes:
- Class instantiation using the `new` keyword. 
- (Anonymous) Class declarations to detect new classes being extended by userland classes. 
- Static use of class properties, constants or functions using the double colon. 
- Function/closure declarations to detect new classes used as parameter type declarations. 
- Function/closure declarations to detect new classes used as return type declarations. 
- Try/catch statements to detect new exception classes being caught. 

PHP version All