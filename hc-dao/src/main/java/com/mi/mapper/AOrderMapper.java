package com.mi.mapper;

import com.mi.dto.OrderOneDTO;
import com.mi.dto.OrderTwoDTO;
import com.mi.dto.OrderZeroDTO;
import com.mi.entity.AppointOrder;
import com.mi.entity.AppointOrderCount;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 订单Dao层
 */
public interface AOrderMapper extends Mapper<AppointOrder> {


    //查询订单列表
    public List<AppointOrder> selectTotalPage(@Param("orderDate") String orderDate);
    //根据订单状态和日期查询用户的总数量
    public OrderZeroDTO customerCount(@Param("orderDate") String orderDate,@Param("theStartTime") String theStartTime,@Param("theEndTime")String theEndTime);

    //根据订单状态和日期查询完成订单的总数量和金额
    public OrderOneDTO finishCount(@Param("orderDate") String orderDate,@Param("theStartTime") String theStartTime,@Param("theEndTime")String theEndTime);

    //根据订单状态和日期查询取消订单的总数量和金额
    public OrderTwoDTO cancelCount(@Param("orderDate") String orderDate,@Param("theStartTime") String theStartTime,@Param("theEndTime")String theEndTime);


    /**
     * 一对一
     * 订单表、订单详情表分页
     * 分页、条件查询记录
     * @param nickName
     * @param orderStatus
     * @return
     */
    public List<AppointOrder> selectByPages(@Param("nickName")String nickName,@Param("orderStatus")Integer orderStatus);


    /**
     * 一对一
     * 订单表、订单详情表删除
     * 删除记录
     * @param orderId
     * @return
     */
    public int deleteByOne(@Param("orderId")String orderId);

    /**
     * 一对一
     * 批量删除
     * @param orderIds
     * @return
     */
    public int batchDelAOrder(List<String> orderIds);

}
