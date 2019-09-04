package com.test.imp;

import com.alibaba.dubbo.config.annotation.Service;

import com.test.mapper.UserMapper;
import domin.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

import java.util.List;


@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return  userMapper.getAll();
    }

}