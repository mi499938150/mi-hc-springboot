package com.mi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2020/4/24
 * @Desc: 订单详情表
 */
@Data
@Table(name = "appoint_order_detail")
public class AppointOrderDetail  implements Serializable {

    /**订单详细表ID**/
    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private String detailId;

    /**订单id**/
    private String orderId;

    /**预约服务id**/
    private String appointId;

    /**预约服务名称**/
    private String appointName;

    /**价格**/
    private BigDecimal appointPrice;

    /**数量**/
    private Integer appointQuantity;

    /**预约时间**/
    private String appointTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**修改时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}