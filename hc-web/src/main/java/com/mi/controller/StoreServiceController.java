package com.mi.controller;

import com.github.pagehelper.PageInfo;
import com.mi.entity.AppointService;
import com.mi.form.AppointServiceForm;
import com.mi.service.AServiceService;
import com.mi.utils.ResponseVoUtil;
import com.mi.utils.TimeUtil;
import com.mi.utils.keyUtil;
import com.mi.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/21
 * @Desc: 预约服务控制器
 */
@RestController
@Slf4j
@RequestMapping("/service")
public class StoreServiceController {

    @Autowired
    private AServiceService aService;

    /**
     * 新增
     * @param serviceForm
     * @return
     */
    @PostMapping(value = "/addService")
    public CommonResponse addService(AppointServiceForm serviceForm){
        log.info("【使用serviceForm方法】");
        log.info(" service = {}",serviceForm);
        // 1. 创建预约服务实体类
        AppointService service = new AppointService();
        // 2. 判断预约服务名称是否为空
        if (!StringUtils.isEmpty(serviceForm.getAppointName())){
            serviceForm.setAppointId(keyUtil.genUniqueKey());
            serviceForm.setCreateTime(TimeUtil.getNowDate(new java.util.Date()));
            BeanUtils.copyProperties(serviceForm,service);
            aService.addAService(service);
           return ResponseVoUtil.success();
        }
        return null;
    }

    /**
     * 分页控制器
     * @param offset
     * @param size
     * @param appointName
     * @param appointTime
     * @param statusType
     * @return
     */
    @GetMapping(value = "/pagesServices")
    public CommonResponse pagesServices(@RequestParam(value = "page",defaultValue = "0")Integer offset,
                                        @RequestParam(value = "size",defaultValue = "10")Integer size,
                                        @RequestParam(value = "appointName",required = false) String appointName,
                                        @RequestParam(value = "appointTime",required = false) String appointTime,
                                        @RequestParam(value = "statusType",required = false) String statusType
                                        ){
        log.info("【使用pagesServices方法】");
        log.info(" appointName = {} , appointTime = {} , appointStatus={}  " ,appointName,appointTime,statusType);
        PageInfo<AppointService> pageInfo = aService.selectByPages(offset,size,appointName,appointTime,statusType);
        return ResponseVoUtil.success(pageInfo);
    }

    /**
     * 更新
     * @param serviceForm
     * @return
     */
    @PostMapping(value = "/updateService")
    public CommonResponse updateService(@RequestBody AppointServiceForm serviceForm){
        log.info("【使用updateService方法】");
        log.info(" update = {}",serviceForm);
        AppointService service = new AppointService();
        serviceForm.setUpdateTime(TimeUtil.getNowDate(new java.util.Date()));
        BeanUtils.copyProperties(serviceForm,service);
        int isUpdate = aService.updateService(service);
        return ResponseVoUtil.success(isUpdate);
    }

    /**
     * 删除
     * @param appointId
     * @return
     */
    @DeleteMapping(value = "/deletetService/{id}")
    public CommonResponse deletetService(@PathVariable("id") String appointId){
        log.info("【deletetService】 = {}",appointId);
        int isDelete = aService.delectByOne(appointId);
       return ResponseVoUtil.success(isDelete);
    }


    @PostMapping(value = "/batchDelService/{appointIds}")
    public CommonResponse batchDelAItem (@PathVariable("appointIds") String appointIds){
        log.info("【batchDelService】");
        log.info("appointIds =  {}",appointIds);
        List<String> idsList = new ArrayList<String>();
        String [] strIds = appointIds.split(",");
        for (String str : strIds){
            idsList.add(str);
        }
        log.info("idsList = {}",idsList);
        int isBatch = aService.batchDelService(idsList);
        return ResponseVoUtil.success(isBatch);
    }

}