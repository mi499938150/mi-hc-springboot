package com.mi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mi.entity.AppointService;
import com.mi.mapper.AServiceMapper;
import com.mi.service.AServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 预约服务实现类
 */
@Service
@Slf4j
public class AServiceServiceImpl  implements AServiceService{

    @Autowired
    private AServiceMapper aServiceMapper;


    @Override
    public List<AppointService> SelectAll() {
        return aServiceMapper.selectAll();
    }

    @Override
    public AppointService SelectByOne(String appointId) {
        return aServiceMapper.selectByPrimaryKey(appointId);
    }

    /**
     * 新增
     * @param service
     * @return
     */
    @Override
    public int addAService(AppointService service) {
        return aServiceMapper.insertSelective(service);
    }

    /**
     * 更新
     * @param service
     * @return
     */
    @Override
    public int updateService(AppointService service) {
        return aServiceMapper.updateByPrimaryKeySelective(service);
    }

    /**
     * 删除
     * @param appointId
     * @return
     */
    @Override
    public int delectByOne(String appointId) {
        return aServiceMapper.deleteByPrimaryKey(appointId);
    }

    /**
     * 分页
     * @param offset
     * @param pageSize
     * @param keywords
     * @param times
     * @param status
     * @return
     */
    @Override
    public PageInfo<AppointService> selectByPages(Integer offset, Integer pageSize, String keywords, String times, String status) {
        PageHelper.startPage(offset,pageSize);
        Example example = new Example(AppointService.class);
        Example.Criteria criteria = example.createCriteria();
        // 1. 查询预约名称
        if (!StringUtils.isEmpty(keywords) && keywords.length() > 0) {
            log.info("appointName = {}",keywords);
            criteria.andLike("appointName","%"+keywords+"%");
        }
        // 2. 查询日期时间
        if (!StringUtils.isEmpty(times) && times.length() > 0){
            log.info("times = {}",times);
            criteria.andEqualTo("appointTime",times);
        }
        // 3. 查询预约状态 0 已预约 1未预约
        if (!StringUtils.isEmpty(status) && status.length() > 0){
            log.info("status = {}",status);
            criteria.andEqualTo("statusType",status);
        }
        // 4. 查询分页
        List<AppointService> servicePages = aServiceMapper.selectByExample(example);
        PageInfo<AppointService> pageInfo = new PageInfo<>(servicePages);
        return pageInfo;
    }

    /**
     * 批量删除
     * @param appointIds
     * @return
     */
    @Override
    public int batchDelService(List<String> appointIds) {
        int isBatch =aServiceMapper.batchDelService(appointIds);
        return isBatch;
    }


}