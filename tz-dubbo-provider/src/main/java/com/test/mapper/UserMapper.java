package com.test.mapper;


import domin.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> getAll();



}
