package com.test.imp;

import com.alibaba.dubbo.config.annotation.Service;
import domin.User;
import com.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import service.TestService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service(version = "1.0.0")
public class TestServiceImpl implements TestService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public String sayHello(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(new Date()) + ": " + str;
    }

    @Override
    public User findUser() {
        return null;
    }
    @Scheduled(initialDelay = 1000,fixedDelay = 5000)
    @Override
    public String addDingshi() {
        System.out.println(new Date());
        return "sss";
    }


}