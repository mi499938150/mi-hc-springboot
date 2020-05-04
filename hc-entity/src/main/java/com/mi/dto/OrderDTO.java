package com.mi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mi.entity.AppointOrderDetail;
import com.mi.enums.OrderStatusEnum;
import com.mi.utils.EnumUtil;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : Rong
 * @date : 2020/4/24
 * @Desc:
 */
@Data
public class OrderDTO {
    /**订单Id**/

    private String orderId;

    /**客户姓名**/
    private String nickName;

    /**客户电话**/
//    private String customer_phone;

    /**订单总金额**/
    private BigDecimal orderAmount;

    /**微信openid**/
    private String openId;

    /**订单状态 默认0 .新订单 1.完结 2.取消**/
    private Integer orderStatus;

    /**订单支付方式    默认0  支付宝    1  微信**/
    private Integer paymentType;

    /**支付状态 0已支付 1 未支付**/
    private Integer payStatus;

    /**下单时间**/
    private Date orderDate;

    /**创建时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**修改时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    // 一对一   一个订单对应一个订单详情
    AppointOrderDetail orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }


}