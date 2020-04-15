package com.mi.service.impl;

import com.mi.entity.User;
import com.mi.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author : Rong
 * @date : 2020/4/15
 * @Desc:
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void addUser(User user) {
        User u = new User();
        u.setUid(user.getUid());
        System.out.println("服务器uid:"+u.getUid());
    }
}