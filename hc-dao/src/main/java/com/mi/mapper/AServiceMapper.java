package com.mi.mapper;

import com.mi.entity.AppointService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 预约服务Dao层
 */
public interface AServiceMapper extends Mapper<AppointService>{

    /**
     * 批量删除
     * @param appointIds
     * @return
     */
    public int batchDelService(List<String> appointIds);
}
