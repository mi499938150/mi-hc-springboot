package com.mi.mapper;

import com.mi.entity.AppointItem;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 服务Dao层
 */
public interface AItemMapper extends Mapper<AppointItem> {

    /**
     * 批量删除
     * @param itemIds
     * @return
     */
    public int batchDelAItem(List<String> itemIds);
}
