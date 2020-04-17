package com.mi.controller;

import com.github.pagehelper.PageInfo;
import com.mi.entity.AppointItem;
import com.mi.entity.AppointService;
import com.mi.form.AppointItemForm;
import com.mi.service.AItemService;
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


import java.sql.Timestamp;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 预约服务控制器
 */

@RestController
@Slf4j
@RequestMapping("/aItem")
public class BuyerAItemController {

    @Autowired
    private AServiceService aService;

    @Autowired
    private AItemService aItemService;


    /**
     * 新增
     * @param itemForm
     * @return
     */
    @PostMapping(value = "/addAItem")
    public CommonResponse addAItem(AppointItemForm itemForm){
        log.info("【使用addAItem方法】");
        //1. 创建服务实体类
        AppointItem item = new AppointItem();
        //2. 判断服务名称是否为空
        if (!StringUtils.isEmpty(itemForm.getItemName())){
            itemForm.setItemId(keyUtil.genUniqueKey());
            BeanUtils.copyProperties(itemForm,item);
            item.setCreateTime(TimeUtil.getNowDate(new java.util.Date()));
            aItemService.addAItem(item);
        }
        return ResponseVoUtil.success();
    }

    /**
     * 查询Items
     * @return
     */
    @GetMapping(value = "/selectAItems")
    public CommonResponse selectAItems(){
        log.info("【使用selectAItems方法】");
        List<AppointItem> items = aItemService.selectByItems();
        return ResponseVoUtil.success(items);
    }

    /**
     * 查询一个Item
     * @param appointId
     * @return
     */
    @GetMapping(value = "/selectAItem/{id}")
    public CommonResponse selectAItem(@PathVariable("id")String appointId){
        log.info("【使用selectAItem 方法】");
        // 1.判断appointid是否为空
        if (!StringUtils.isEmpty(appointId)){
            AppointItem item = aItemService.seleceByOne(appointId);
            return ResponseVoUtil.success(item);
        }
        // 2. 为空就返回null
        return null;
    }

    /**
     * 删除一个Item
     * @param appointId
     * @return
     */
    @DeleteMapping(value = "/deletetAItem/{id}")
    public CommonResponse deletetAItem(@PathVariable("id")String appointId){
        log.info("【使用delectAItem】");
        // 1.判断appointid是否为空
        if (!StringUtils.isEmpty(appointId)){
            int isDelete = aItemService.delectByOne(appointId);
            return ResponseVoUtil.success(isDelete);
        }
        // 2. 为空就返回null
        return null;
    }

    @GetMapping(value = "/pagesAItems")
    public CommonResponse pagesAItems(@RequestParam(value = "page",defaultValue = "0")Integer offset,
                                      @RequestParam(value = "size",defaultValue = "10")Integer size){
        log.info("【使用pagesAItems】");
        if (!StringUtils.isEmpty(offset) && !StringUtils.isEmpty(size)){
            PageInfo<AppointItem> pageInfo = aItemService.selectByPages(offset,size);
          return   ResponseVoUtil.success(pageInfo);
        }
        return null;
    }

}