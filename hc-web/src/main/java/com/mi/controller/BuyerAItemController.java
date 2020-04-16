package com.mi.controller;

import com.mi.entity.AppointService;
import com.mi.service.AServiceService;
import com.mi.utils.ResponseVoUtil;
import com.mi.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 预约服务控制器
 */

@RestController
@Slf4j
public class BuyerAItemController {

    @Autowired
    private AServiceService aService;

    @GetMapping(value = "/selectByServices")
    public CommonResponse<AppointService> selectByServices(){
        List<AppointService> appointServices = aService.SelectAll();
        log.info("[获取服务名称数据] result = {}",appointServices);
      return   ResponseVoUtil.success(appointServices);
    }

    @GetMapping(value = "/selectByService/{id}")
    public CommonResponse selectByService(@PathVariable("id") String appointId){
       AppointService appointService = aService.SelectByOne(appointId);
       return ResponseVoUtil.success(appointService);
    }

    @PostMapping(value = "/addAService")
    public void addAService(AppointService appointService){
        log.info("[开始添加数据...]");

    }
}