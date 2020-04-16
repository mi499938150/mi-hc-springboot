package com.mi.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 订单
 */

@Data
@Table(name = "appoint_order")
public class AppointOrder   implements Serializable {

    /**订单Id**/
    @Id
    @KeySql(useGeneratedKeys = true) //回显
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

    /**预约日期**/
    private Date orderWeek;

    /**预约时间**/
    private String orderTime;

    /**订单支付方式    默认0  支付宝    1  微信**/
    private Integer paymentType;

    /**支付状态**/
    private Integer payStatus;

    /**创建时间**/
    private Timestamp createTime;

    /**修改时间**/
    private Timestamp updateTime;

}