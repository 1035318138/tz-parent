package com.test.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domin.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import result.PageResult;
import result.Status;
import service.UserService;
import utils.AesUtil;

import java.io.IOException;
import java.util.*;

/**
 * 用户控制器
 *
 * @author zym
 * @create 2018-06-19 14:27
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference(version = "1.0.0")
    private UserService userService;

    @Value("${AESkey}")
    private String Aeskey;



    @GetMapping
    public PageResult user(Integer pageNow, Integer pageSize) {
        PageResult list = userService.getAll(pageNow,pageSize);
        List<User> users = (List<User>) list.getList();
        for(User i :users){
            byte[] bytes = AesUtil.parseHexStr2Byte(i.getPassword());
            byte[] decrypt = AesUtil.decrypt(bytes, Aeskey);
            String s = new String(decrypt);
            i.setPassword(s);
        }
        return list;
    }
    @GetMapping("test")
    public List<Map> test(){
        List list = new ArrayList<>();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id","1");
        map.put("name","张三");
        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("id","2");
        map1.put("name","李四");
        list.add(map);
        list.add(map1);
        return list;
    }


    @GetMapping("login")
    public String login(String name,String password){
        byte[] encrypt = AesUtil.encrypt(password, Aeskey);
        String s = AesUtil.parseByte2HexStr(encrypt);


        return userService.login(name,s);
    }


//    @PostMapping
//    public Map Aes(@RequestBody String body) throws IOException {
//        System.out.println(body);
//        String AesKey="123xyz";//密钥
//        ObjectMapper mapper = new ObjectMapper();
//        Map map = mapper.readValue(body, Map.class);
//        String jiami = String.valueOf(map.get("jiami"));//获取到加密json字符串
//
//        byte[] bytes = AesUtil.parseHexStr2Byte(jiami);
//        byte[] decrypt = AesUtil.decrypt(bytes, AesKey);
//        String s = new String(decrypt);
//        System.out.println(s);
//
//        Map map1 = mapper.readValue(s, Map.class);
//        return map1;
//
//
//    }

    @PutMapping
    public Integer insert(@RequestBody User user){


        byte[] bytes = AesUtil.encrypt(user.getPassword(), Aeskey);
        String pwd = AesUtil.parseByte2HexStr(bytes);
        user.setPassword(pwd);
        try {
           userService.insertUser(user);
           return Status.Success_insert;
       }catch (Exception e){
            return Status.Fail_insert;
       }
    }

    @DeleteMapping
    public Integer delete(String ids){
        String[] split = ids.split(",");
        Integer[] id=new Integer[split.length];
        for(int i=0;i<id.length;i++){
            id[i]=Integer.valueOf(split[i]);
        }

        try {
           userService.delete(id);
            return Status.Success_delete;
        }catch (Exception e){
            return Status.Fail_delete;
        }
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id")Integer id){
        User user = userService.findUserById(id);
        byte[] bytes = AesUtil.parseHexStr2Byte(user.getPassword());
        byte[] decrypt = AesUtil.decrypt(bytes, Aeskey);
        String pwd = new String(decrypt);
        user.setPassword(pwd);
        return user;
    }

    @PostMapping
    public Integer update(@RequestBody User user){
        try {
            byte[] encrypt = AesUtil.encrypt(user.getPassword(), Aeskey);
            String s = AesUtil.parseByte2HexStr(encrypt);
            user.setPassword(s);
            userService.update(user);
            return Status.Success_update;
        }catch (Exception e){
            return Status.Fail_update;
        }
    }


}
