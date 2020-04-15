package com.mi.controller;

import com.mi.entity.User;
import com.mi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Rong
 * @date : 2020/4/15
 * @Desc: 测试控制器
 */

@RestController
public class Test {

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public String Test(){
        User user = new User();
        user.setUid("111");
        userService.addUser(user);
        String str = user.getUid();
        return str;
    }
}