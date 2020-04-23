package com.mi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mi.entity.AppointService;
import com.mi.entity.WeChatUser;
import com.mi.mapper.WCUserMapper;
import com.mi.service.WCUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/23
 * @Desc:   获取微信用户接口实现类
 */
@Service
@Slf4j
public class WCUserServiceImpl implements WCUserService {

    @Autowired
    private WCUserMapper wcUserMapper;


    @Override
    public int delectByOne(String openId) {
        return wcUserMapper.deleteByPrimaryKey(openId);
    }

    /**
     * 分页
     * @param offset
     * @param pageSize
     * @param keywords
     * @return
     */
    @Override
    public PageInfo<WeChatUser> selectByPages(Integer offset, Integer pageSize, String keywords) {
        PageHelper.startPage(offset,pageSize);
        Example example = new Example(WeChatUser.class);
        Example.Criteria criteria = example.createCriteria();
        // 1. 查询keywords 关键字
        if (!StringUtils.isEmpty(keywords) && keywords.length() > 0) {
            log.info("nickName = {}",keywords);
            criteria.andLike("nickName","%"+keywords+"%");
        }
        List<WeChatUser>  weChatUsers = wcUserMapper.selectByExample(example);
        PageInfo<WeChatUser> weChatUserPage = new PageInfo<>(weChatUsers);
        return weChatUserPage;
    }

    @Override
    public int batchDelWechats(List<String> openIds) {
        int isBatch = wcUserMapper.batchDelService(openIds);
        return isBatch;
    }
}