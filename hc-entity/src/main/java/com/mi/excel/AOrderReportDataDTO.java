package com.mi.excel;

import com.mi.entity.AppointOrder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Rong
 * @date : 2020/5/5
 * @Desc:  导出订单统计实体类
 */

@Data
public class AOrderReportDataDTO implements Serializable {

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

    /**一对多   对应多个订单详情数据**/
    List<AppointOrder> appoints;
}