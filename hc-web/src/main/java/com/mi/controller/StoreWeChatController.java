package com.mi.controller;

import com.github.pagehelper.PageInfo;
import com.mi.entity.WeChatUser;
import com.mi.service.WCUserService;
import com.mi.utils.ResponseVoUtil;
import com.mi.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/23
 * @Desc: 获取微信用户控制器
 */

@RestController
@Slf4j
@RequestMapping(value = "/wechat")
public class StoreWeChatController {


    @Autowired
    private WCUserService wcUserService;


    @GetMapping("/pagesWeChats")
    public CommonResponse pagesWeChats (@RequestParam(value = "page",defaultValue = "0")Integer offset,
                                        @RequestParam(value = "size",defaultValue = "10")Integer size
            ,  @RequestParam(value = "nickName",required = false) String nickName){
        log.info("【pagesWeChats】");
        log.info(" nickName = {}",nickName);
        PageInfo<WeChatUser> pageInfo = wcUserService.selectByPages(offset,size,nickName);
        return ResponseVoUtil.success(pageInfo);
    }


    /**
     * 删除
     * @param openId
     * @return
     */
    @DeleteMapping(value = "/deletetWechat/{id}")
    public CommonResponse deletetWechat(@PathVariable("id") String openId){
        log.info("【deletetWechat】 = {}",openId);
        int isDelete = wcUserService.delectByOne(openId);
        return ResponseVoUtil.success(isDelete);
    }

    /**
     * 批量删除
     * @param openIds
     * @return
     */
    @PostMapping(value = "/batchDelWechats/{openIds}")
    public CommonResponse batchDelWechats (@PathVariable("openIds") String openIds){
        log.info("【batchDelService】");
        log.info("openIds =  {}",openIds);
        List<String> idsList = new ArrayList<String>();
        String [] strIds = openIds.split(",");
        for (String str : strIds){
            idsList.add(str);
        }
        log.info("idsList = {}",idsList);
        int isBatch = wcUserService.batchDelWechats(idsList);
        return ResponseVoUtil.success(isBatch);
    }

}