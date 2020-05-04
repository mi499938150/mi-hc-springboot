package com.mi.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : Rong
 * @date : 2020/4/29
 * @Desc:  根据订单状态和日期查询完成订单的总数量和金额
 */
@Data
public class OrderOneDTO implements Serializable {

    /** 完成订单数量**/
    private Integer finishCount;

    /** 完成订单金额**/
    private BigDecimal finishAmount;

}