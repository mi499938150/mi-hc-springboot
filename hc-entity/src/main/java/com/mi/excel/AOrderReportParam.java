package com.mi.excel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2020/5/5
 * @Desc:  导出订单传入参数实体类
 */
@Data
public class AOrderReportParam implements Serializable{
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

    /**周月**/
    private String orderDate;

    /**时间段**/
    private String time;
}