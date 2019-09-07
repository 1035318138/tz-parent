package com.test.imp;

import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.mapper.UserMapper;


import domin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import result.PageResult;
import service.UserService;

import java.util.Date;
import java.util.List;


@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult getAll(Integer pageNow, Integer pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<User> users = userMapper.selectAll();

        PageInfo<User> pageInfo = new PageInfo<User>(users);
        long total = pageInfo.getTotal();
        List<User> list = pageInfo.getList();
        PageResult result = new PageResult();
        result.setList(list);
        result.setTotal(total);



        return result;
    }

    @Override
    public void test() {
        User user = new User();
        user.setName("six");
        user.setEndtime(new Date());
        user.setEmail("ll@qq.com");
        user.setImgurl("xxx");
        user.setPassword("123");
        user.setTel(15989563254L);
        userMapper.add(user);
    }

    @Override
    public String login(String name, String password) {
        User u=userMapper.login(name,password);
        if(u!=null){
            User user = new User();
            user.setId(u.getId());
            user.setEndtime(new Date());
            userMapper.updateByPrimaryKeySelective(user);
            return "ok";
        }
        return "no";
    }

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }


    @Override
    public void delete(Integer[] ids) {
        for (Integer i:ids) {
            System.out.println(i);
            userMapper.deleteByPrimaryKey(i);
        }
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }


}