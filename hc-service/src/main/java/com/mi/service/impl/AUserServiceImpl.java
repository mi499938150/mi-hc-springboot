package com.mi.service.impl;

import com.mi.entity.AUser;
import com.mi.mapper.AUserMapper;
import com.mi.service.AUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author : Rong
 * @date : 2020/5/6
 * @Desc:
 */
@Service
@Slf4j
public class AUserServiceImpl implements AUserService {

    @Autowired
    private AUserMapper aUserMapper;

    @Override
    public boolean checkUser(String loginName, String passWord) {
        Example example = new Example(AUser.class);
        Example.Criteria criteria = example.createCriteria();
        log.info("[loginName = {} , password = {} ]",loginName,passWord);
        if (!StringUtils.isEmpty(loginName) && loginName.length() > 0){
            criteria.andEqualTo("userName",loginName);
        }
        if (!StringUtils.isEmpty(passWord) && passWord.length() > 0){
            criteria.andEqualTo("passWord",passWord);
        }
        AUser aUser = aUserMapper.selectOneByExample(example);
        if (!StringUtils.isEmpty(aUser) && aUser!=null){
            return true;
        }
        return false;
    }

    @Override
    public AUser getUser(String loginName) {
        Example example = new Example(AUser.class);
        Example.Criteria criteria = example.createCriteria();
        log.info("[loginName = {} ]",loginName);
        if (!StringUtils.isEmpty(loginName) && loginName.length() > 0){
            criteria.andEqualTo("userName",loginName);
        }
        AUser aUser = aUserMapper.selectOneByExample(example);
        if (!StringUtils.isEmpty(aUser) && aUser!=null){
            return aUser;
        }
        return null;
    }
}