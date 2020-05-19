<?php

namespace Test;

class User
{
    public function setUsernameAttribute($value)
    {
        $notUsed = 1;
        $this->attributes['username'] = "123username";
        str_replace("username", "name", "");
    }
}
