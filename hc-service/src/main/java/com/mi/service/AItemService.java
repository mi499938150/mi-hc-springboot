package com.mi.service;

import com.github.pagehelper.PageInfo;
import com.mi.entity.AppointItem;
import com.mi.form.AppointItemForm;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 服务接口
 */
public interface AItemService {

    public void addAItem(AppointItem item);

    public List<AppointItem> selectByItems();

    public AppointItem seleceByOne(String itemId);

    public int delectByOne(String itemId);

    public int updateItem(AppointItem item);

    /**
     * 分页
     * @param offset
     * @param pageSize
     * @return
     */
    public PageInfo<AppointItem> selectByPages(Integer offset, Integer pageSize,String keywords);

    /**
     * 批量删除
     * @param itemIds
     * @return
     */
    public int batchDelAItem(List<String> itemIds);
}