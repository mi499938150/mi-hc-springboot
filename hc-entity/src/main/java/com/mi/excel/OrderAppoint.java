package com.mi.excel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mi.entity.AppointOrderDetail;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2020/5/5
 * @Desc:  订单列表实体类
 */
@Data
public class OrderAppoint  implements Serializable{

    /**订单Id**/
    private String orderId;

    /**客户姓名**/
    private String nickName;

    /**订单总金额**/
    private BigDecimal orderAmount;

    /**微信openid**/
//    private String openId;

    /**订单状态 默认0 .新订单 1.完结 2.取消**/
    private Integer orderStatus;

    /**下单时间**/
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orderDate;

    /**订单支付方式    默认0  支付宝    1  微信**/
//    private Integer paymentType;

    /**支付状态 0已支付 1 未支付**/
//    private Integer payStatus;

    /**创建时间**/
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    private Date createTime;

    /**修改时间**/
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    private Date updateTime;
}