This standard covers all array declarations, regardless of the number and type of values contained within the array.

Valid: array keyword lowercase
```
$array = array('val1', 'val2');
```

Invalid: first letter capitalised
```
$array = Array('val1', 'val2');
```

Valid: first key on second line
```
$array = array(
          'key1' => 'value1',
          'key2' => 'value2',
         );
```

Invalid: first key on same line
```
$array = array('key1' => 'value1',
          'key2' => 'value2',
         );
```

Valid: aligned correctly
```
$array = array(
          'key1' => 'value1',
          'key2' => 'value2',
         );
```

Invalid: keys and parenthesis aligned incorrectly
```
$array = array(
         'key1' => 'value1',
         'key2' => 'value2',
);
```

Valid: keys and values aligned
```
$array = array(
          'keyTen'    => 'ValueTen',
          'keyTwenty' => 'ValueTwenty',
         );
```

Invalid: alignment incorrect
```
$array = array(
          'keyTen' => 'ValueTen',
          'keyTwenty' => 'ValueTwenty',
         );
```

Valid: comma after each value
```
$array = array(
          'key1' => 'value1',
          'key2' => 'value2',
          'key3' => 'value3',
         );
```

Invalid: no comma after last value
```
$array = array(
          'key1' => 'value1',
          'key2' => 'value2',
          'key3' => 'value3' 
         );
```
