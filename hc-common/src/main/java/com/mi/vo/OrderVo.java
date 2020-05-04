package com.mi.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : Rong
 * @date : 2020/4/29
 * @Desc: 拼订单的数据
 */
@Data
public class OrderVo implements Serializable {

    /**预约用户数量**/
    private Integer cutomerCount;

    /** 完成订单数量**/
    private Integer finishCount;

    /** 完成订单金额**/
    private BigDecimal finishAmount;

    /**取消订单的数量**/
    private Integer cancelCount;

    /**取消订单的金额**/
    private BigDecimal cancelAmount;
}