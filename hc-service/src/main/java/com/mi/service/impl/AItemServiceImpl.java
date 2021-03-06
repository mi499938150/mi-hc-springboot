package com.mi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mi.entity.AppointItem;
import com.mi.mapper.AItemMapper;
import com.mi.service.AItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 服务实现类
 */
@Service
@Slf4j
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
     * @param itemId
     * @return
     */
    @Override
    public AppointItem seleceByOne(String itemId) {
        return aItemMapper.selectByPrimaryKey(itemId);
    }

    /**
     * 删除一个Item
     * @param itemId
     */
    @Override
    public int delectByOne(String itemId) {
        return aItemMapper.deleteByPrimaryKey(itemId);
    }

    /**
     * 更新
     * @param item
     * @return
     */
    @Override
    public int updateItem(AppointItem item) {
        return aItemMapper.updateByPrimaryKeySelective(item);
    }

    /**
     *  分页
     * @param offset
     * @param pageSize
     * @param itemName
     * @return
     */
    @Override
    public PageInfo<AppointItem> selectByPages(Integer offset, Integer pageSize,String itemName) {
        PageHelper.startPage(offset,pageSize);
        Example example = new Example(AppointItem.class);
        Example.Criteria criteria = example.createCriteria();

        // 1. 查询keywords 关键字
        if (!StringUtils.isEmpty(itemName) && itemName.length() > 0) {
            log.info("itemName = {}",itemName);
            criteria.andLike("itemName","%"+itemName+"%");
        }
        List<AppointItem> itemPages = aItemMapper.selectByExample(example);
        PageInfo<AppointItem> pageInfo = new PageInfo<>(itemPages);
        return pageInfo;
    }

    /**
     * 批量删除
     * @param itemIds
     * @return
     */
    @Override
    public int batchDelAItem(List<String> itemIds) {
        int isBatch  = aItemMapper.batchDelAItem(itemIds);
        return isBatch;
    }

}