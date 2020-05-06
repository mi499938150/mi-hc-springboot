package com.mi.controller;

import com.mi.entity.AppointService;
import com.mi.exception.HcException;
import com.mi.service.AServiceService;
import com.mi.utils.ResponseVoUtil;
import com.mi.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/15
 * @Desc: 测试控制器
 */

@RestController
public class Test {

    @Autowired
    private AServiceService aServiceService;

    @RequestMapping("test")
    public CommonResponse test() throws HcException{
        List<AppointService> appointServices = aServiceService.SelectAll();
        int i = 2/0;
        System.out.println("i:"+i);
       return ResponseVoUtil.success(appointServices);
    }

}