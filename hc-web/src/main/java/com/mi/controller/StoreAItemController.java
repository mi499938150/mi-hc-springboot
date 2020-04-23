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

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 服务控制器
 */

@RestController
@Slf4j
@RequestMapping("/aItem")
public class StoreAItemController {



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
     * @param itemId
     * @return
     */
    @GetMapping(value = "/selectAItem/{id}")
    public CommonResponse selectAItem(@PathVariable("id")String itemId){
        log.info("【使用selectAItem 方法】  = {}",itemId);
        // 1.判断appointid是否为空
        if (!StringUtils.isEmpty(itemId)){
            AppointItem item = aItemService.seleceByOne(itemId);
            return ResponseVoUtil.success(item);
        }
        // 2. 为空就返回null
        return null;
    }

    /**
     * 删除一个Item
     * @param itemId
     * @return
     */
    @DeleteMapping(value = "/deletetAItem/{id}")
    public CommonResponse deletetAItem(@PathVariable("id")String itemId){
        log.info("【使用delectAItem】 = {}",itemId);
        // 1.判断appointid是否为空

            int isDelete = aItemService.delectByOne(itemId);
            return ResponseVoUtil.success(isDelete);

    }

    /**
     * 分页
     * @param offset
     * @param size
     * @param itemName
     * @return
     */
    @GetMapping(value = "/pagesAItems")
    public CommonResponse pagesAItems(@RequestParam(value = "page",defaultValue = "0")Integer offset,
                                      @RequestParam(value = "size",defaultValue = "10")Integer size
                                     ,  @RequestParam(value = "itemName",required = false) String itemName
    ){
        log.info("【使用pagesAItems】");
        log.info(" itemName = {}",itemName);
        PageInfo<AppointItem> pageInfo = aItemService.selectByPages(offset,size,itemName);

          return   ResponseVoUtil.success(pageInfo);
    }

    /**
     * 更新预约服务
     * @param itemForm
     * @return
     */
    @PostMapping(value = "updateAItem")
    public CommonResponse updateAItem(@RequestBody AppointItemForm itemForm){
        log.info("【使用updateAItem】");
        log.info(" item = {}",itemForm);

        AppointItem item = new AppointItem();
        if (!StringUtils.isEmpty(itemForm.getItemName())){
            BeanUtils.copyProperties(itemForm,item);
            item.setUpdateTime(TimeUtil.getNowDate(new java.util.Date()));
            int isUpdate = aItemService.updateItem(item);
            return ResponseVoUtil.success(isUpdate);
        }
        return null;
    }

    /**
     * 批量删除
     * @param itemIds
     * @return
     */
    @PostMapping(value = "/batchDelAItem/{itemIds}")
    public CommonResponse batchDelAItem (@PathVariable("itemIds") String itemIds){
         log.info("【使用batchDelAItem】");
         log.info("itemIds =  {}",itemIds);
         List<String> idsList = new ArrayList<String>();
         String [] strIds = itemIds.split(",");
         for (String str : strIds){
             idsList.add(str);
         }
         log.info("idsList = {}",idsList);
         int isBatch = aItemService.batchDelAItem(idsList);
        return ResponseVoUtil.success(isBatch);
    }
}