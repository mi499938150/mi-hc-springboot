package com.mi.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : Rong
 * @date : 2020/4/29
 * @Desc: 根据订单状态和日期查询取消订单的总数量和金额
 */
@Data
public class OrderTwoDTO implements Serializable {

    /**取消订单的数量**/
    private Integer cancelCount;

    /**取消订单的金额**/
    private BigDecimal cancelAmount;

}