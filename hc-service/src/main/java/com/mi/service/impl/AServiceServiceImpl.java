package com.mi.service.impl;

import com.mi.entity.AppointService;
import com.mi.mapper.AServiceMapper;
import com.mi.service.AServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}