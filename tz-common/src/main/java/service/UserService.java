package service;


import domin.User;

import java.util.List;

/**
 * 用户接口
 *
 * @author zym
 * @create 2018-06-19 14:10
 **/
public interface UserService {
    List<User> getAll();
}
