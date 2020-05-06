package com.mi.controller;

import com.mi.entity.AUser;
import com.mi.service.AUserService;
import com.mi.utils.JwtUtil;
import com.mi.utils.ResponseVoUtil;
import com.mi.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;

/**
 * @author : Rong
 * @date : 2020/5/6
 * @Desc:
 */
@RestController
@Slf4j
public class StroeLoginController {

    @Autowired
    private AUserService aUserService;

    @GetMapping("/login")
    public CommonResponse login(AUser aUser) {

        boolean isLogin = aUserService.checkUser(aUser.getUserName(), aUser.getPassWord());
        if (isLogin) {
            //模拟数据库查询
            AUser user = aUserService.getUser(aUser.getUserName());
            if (user != null) {
                //返回token
                String token = JwtUtil.sign(aUser.getUserName(), aUser.getPassWord());
                if (token != null) {
                    return ResponseVoUtil.success(token);
                }
            }
        }
        return ResponseVoUtil.error(300, "操作失败");
    }

    @PostMapping("getUser")
    public CommonResponse getUserInfo(HttpServletRequest request, AUser aUser) {
        String token = request.getHeader("token");
        log.info(" token = {} ",token);
        boolean verity = JwtUtil.verity(token);
        if (verity) {
            AUser auser = aUserService.getUser(aUser.getUserName());
            if (aUser != null) {
                return ResponseVoUtil.success(aUser);
            }
        }
        return ResponseVoUtil.error(300, "操作失败");
    }

}