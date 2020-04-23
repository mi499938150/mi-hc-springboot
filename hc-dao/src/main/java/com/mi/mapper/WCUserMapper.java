package com.mi.mapper;

import com.mi.entity.WeChatUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 微信Dao层
 */
public interface WCUserMapper extends Mapper<WeChatUser> {


    /**
     * 批量删除
     * @param openIds
     * @return
     */
    public int batchDelService(List<String> openIds);
}
