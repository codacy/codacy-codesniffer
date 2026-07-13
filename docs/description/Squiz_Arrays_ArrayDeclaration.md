This standard covers all array declarations, regardless of the number and type of values contained within the array.

Valid: Array keyword lowercase.
```
$array = array('val1', 'val2');
```

Invalid: First letter capitalised.
```
$array = Array('val1', 'val2');
```

Valid: First key on second line.
```
$array = array(
          'key1' => 'value1',
          'key2' => 'value2',
         );
```

Invalid: First key on same line.
```
$array = array('key1' => 'value1',
          'key2' => 'value2',
         );
```

Valid: Aligned correctly.
```
$array = array(
          'key1' => 'value1',
          'key2' => 'value2',
         );
```

Invalid: Keys and parenthesis aligned incorrectly.
```
$array = array(
         'key1' => 'value1',
         'key2' => 'value2',
);
```

Valid: Keys and values aligned.
```
$array = array(
          'keyTen'    => 'ValueTen',
          'keyTwenty' => 'ValueTwenty',
         );
```

Invalid: Alignment incorrect.
```
$array = array(
          'keyTen' => 'ValueTen',
          'keyTwenty' => 'ValueTwenty',
         );
```

Valid: Comma after each value.
```
$array = array(
          'key1' => 'value1',
          'key2' => 'value2',
          'key3' => 'value3',
         );
```

Invalid: No comma after last value.
```
$array = array(
          'key1' => 'value1',
          'key2' => 'value2',
          'key3' => 'value3' 
         );
```
