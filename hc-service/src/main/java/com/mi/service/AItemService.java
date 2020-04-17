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

    public AppointItem seleceByOne(String appointId);

    public int delectByOne(String appointId);

    /**
     * 分页
     * @param offset
     * @param pageSize
     * @return
     */
    public PageInfo<AppointItem> selectByPages(Integer offset, Integer pageSize);
}