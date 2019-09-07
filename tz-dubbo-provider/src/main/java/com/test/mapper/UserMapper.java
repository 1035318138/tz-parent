package com.test.mapper;



import domin.User;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.BaseMapper;


import java.util.List;


public interface UserMapper  extends BaseMapper<User> {

    @Select("SELECT * FROM user")
    List<User> getAll();

    @Insert("insert into user values(null,'1','1',null,'1',1,'1')")
    void add(User user);
    @Select("select * from user where name=#{name} and password=#{password}")
    User login(@Param("name") String name, @Param("password") String password);
}
