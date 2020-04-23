package com.mi.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
 
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2020/4/23
 * @Desc:
 */
@Data
public class AppointOrderForm {


    private String orderId;

    /**客户姓名**/
    private String nickName;

    /**客户电话**/
    private String customer_phone;

    /**订单总金额**/
    private BigDecimal orderAmount;

    /**微信openid**/
    private String openId;

    /**订单状态 默认0 .新订单 1.完结 2.取消**/
    private Integer orderStatus;

//    /**预约日期**/
//    private Date orderWeek;

    /**预约时间**/
    private String orderTime;

    /**订单支付方式    默认0  支付宝    1  微信**/
    private Integer paymentType;

    /**支付状态**/
    private Integer payStatus;

    /**创建时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**修改时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}