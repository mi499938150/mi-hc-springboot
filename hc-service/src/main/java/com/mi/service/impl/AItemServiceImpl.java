package com.mi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mi.entity.AppointItem;
import com.mi.mapper.AItemMapper;
import com.mi.service.AItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 服务实现类
 */
@Service
public class AItemServiceImpl implements AItemService {

    @Autowired
    private AItemMapper aItemMapper;

    /**
     * 新增
     * @param item
     */
    @Override
    public void addAItem(AppointItem item) {
        aItemMapper.insertSelective(item);
    }

    /**
     * 查询所有Items
     * @return
     */
    @Override
    public List<AppointItem> selectByItems() {
        return aItemMapper.selectAll();
    }

    /**
     * 查询一个Item
     * @param appointId
     * @return
     */
    @Override
    public AppointItem seleceByOne(String appointId) {
        return aItemMapper.selectByPrimaryKey(appointId);
    }

    /**
     * 删除一个Item
     * @param appointId
     */
    @Override
    public int delectByOne(String appointId) {
        return aItemMapper.deleteByPrimaryKey(appointId);
    }

    /**
     * 分页
     * @param offset
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<AppointItem> selectByPages(Integer offset, Integer pageSize) {
        PageHelper.startPage(offset,pageSize);
        List<AppointItem> itemPages = aItemMapper.selectAll();
        PageInfo<AppointItem> pageInfo = new PageInfo<>(itemPages);
        return pageInfo;
    }

}