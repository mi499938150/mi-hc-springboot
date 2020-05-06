package com.mi.service;

import com.mi.entity.AUser;

/**
 * @author : Rong
 * @date : 2020/4/15
 * @Desc:
 */
public interface AUserService {

    /**
     * 校验用户信息
     * @param loginName
     * @param passWord
     * @return
     */
    boolean checkUser(String loginName, String passWord);

    /**
     * 查询用户信息
     * @param loginName
     * @return
     */
    AUser getUser(String loginName);

}