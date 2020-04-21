Verifies that no database related PHP functions are used."Avoid touching the database directly. If there is a defined function that can get
 the data you need, use it. Database abstraction (using functions instead of queries)
 helps keep your code forward-compatible and, in cases where results are cached in memory,
 it can be many times faster."