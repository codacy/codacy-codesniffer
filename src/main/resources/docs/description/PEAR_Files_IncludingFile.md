Anywhere you are unconditionally including a class file, use <em>require_once</em>. Anywhere you are conditionally including a class file (for example, factory methods), use <em>include_once</em>. Either of these will ensure that class files are included only once. They share the same file list, so you don't need to worry about mixing them - a file included with <em>require_once</em> will not be included again by <em>include_once</em>.

Valid: used as statement
```
require_once 'PHP/CodeSniffer.php';
```

Invalid: used as function
```
require_once('PHP/CodeSniffer.php');
```
