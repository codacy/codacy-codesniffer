The strict_types declaration must be present.

Valid: `strict_types` declaration is present.
```
declare(strict_types=1);

declare(encoding='UTF-8', strict_types=0);
```

Invalid: Missing `strict_types` declaration.
```
declare(encoding='ISO-8859-1');
```

Valid: `strict_types` declaration is enabled.
```
declare(strict_types=1);
```

Invalid: `strict_types` declaration is disabled.
```
declare(strict_types=0);
```
