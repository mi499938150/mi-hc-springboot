package com.mi.service;

import com.github.pagehelper.PageInfo;
import com.mi.entity.AppointService;
import com.mi.form.AppointServiceForm;

import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 预约服务接口
 */
public interface AServiceService {

   public List<AppointService> SelectAll();

   public AppointService SelectByOne(String appointId);

   public int addAService(AppointService service);

   public int updateService(AppointService service);

   public int delectByOne(String appointId);

    /**
     * 分页
     * @param offset
     * @param pageSize
     * @param keywords
     * @param times
     * @param status
     * @return
     */
   public PageInfo<AppointService> selectByPages(Integer offset, Integer pageSize,String keywords,String times,String status);


   /**
    * 批量删除
    * @param appointIds
    * @return
    */
   public int batchDelService(List<String> appointIds);
}