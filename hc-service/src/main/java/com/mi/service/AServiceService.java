package com.mi.service;

import com.mi.entity.AppointService;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 预约服务接口
 */
public interface AServiceService {

    List<AppointService> SelectAll();

    AppointService SelectByOne(String appointId);
}