package com.mi.service;

import com.github.pagehelper.PageInfo;
import com.mi.entity.AppointService;
import com.mi.entity.WeChatUser;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/23
 * @Desc: 获取微信用户接口
 */
public interface WCUserService {


    public  int delectByOne(String openId);

    /**
     * 分页
     * @param offset
     * @param pageSize
     * @param keywords
     * @return
     */
    public PageInfo<WeChatUser> selectByPages(Integer offset, Integer pageSize, String keywords);


    /**
     * 批量删除
     * @param openIds
     * @return
     */
    public int batchDelWechats(List<String> openIds);
}
