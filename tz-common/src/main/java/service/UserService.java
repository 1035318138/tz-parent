package service;


import domin.User;
import result.PageResult;

import java.util.List;

/**
 * 用户接口
 *
 * @author zym
 * @create 2018-06-19 14:10
 **/
public interface UserService {
    PageResult getAll(Integer pageNo, Integer pageSize);


    void test();

    String login(String name, String password);

    void insertUser(User user);

    void delete(Integer[] ids);

    User findUserById(Integer id);

    void update(User user);
}
