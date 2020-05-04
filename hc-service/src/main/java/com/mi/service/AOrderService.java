package com.mi.service;

import com.github.pagehelper.PageInfo;
import com.mi.dto.OrderDTO;
import com.mi.dto.OrderOneDTO;
import com.mi.dto.OrderTwoDTO;
import com.mi.dto.OrderZeroDTO;
import com.mi.entity.AppointOrder;
import com.mi.entity.AppointOrderCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Rong
 * @date : 2020/4/24
 * @Desc: 订单表接口
 */
public interface AOrderService {

    //查看订单列表分页
    public PageInfo<AppointOrder> selectTotalPage(Integer offset, Integer pageSize,String orderDate);
    //根据订单状态和日期查询用户的总数量
    public OrderZeroDTO customerCount(String orderDate);

    //根据订单状态和日期查询完成订单的总数量和金额
    public OrderOneDTO finishCount(String orderDate);

    //根据订单状态和日期查询取消订单的总数量和金额
    public OrderTwoDTO cancelCount(String orderDate);



    public int cancelOrder(OrderDTO orderDTO);

    /**
     * 订单完成
     * @param orderDTO
     * @return
     */
    public int finishOrder(OrderDTO orderDTO);

    /**
     * 一对一删除
     * @param orderId
     * @return
     */
    public int deleteByOne(String orderId);

    /**
     * 分页记录
     * @param offset
     * @param pageSize
     * @param nickName
     * @param orderStatus
     * @return
     */
    PageInfo<OrderDTO> selectByPages(Integer offset, Integer pageSize,String nickName,Integer orderStatus);

    /**
     * 批量删除
     * @param orderIds
     * @return
     */
    public int batchDelAOrder(List<String> orderIds);


}
