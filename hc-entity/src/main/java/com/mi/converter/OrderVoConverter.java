package com.mi.converter;

import com.mi.dto.OrderOneDTO;
import com.mi.dto.OrderTwoDTO;
import com.mi.dto.OrderZeroDTO;
import com.mi.vo.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * @author : Rong
 * @date : 2020/4/29
 * @Desc:  订单数据拼接转换
 */
public class OrderVoConverter {

    public static OrderVo convert(OrderZeroDTO zeroDTO, OrderOneDTO orderOneDTO, OrderTwoDTO twoDTO){
        OrderVo orderVo = new OrderVo();
        //  1.判断用户下单数量
        if (!StringUtils.isEmpty(zeroDTO.getCutomerCount()) && zeroDTO.getCutomerCount() != null){
            orderVo.setCutomerCount(zeroDTO.getCutomerCount());
        }else {

            orderVo.setCutomerCount(0);
        }
        // 2.判断完成订单的数量
        if (!StringUtils.isEmpty(orderOneDTO.getFinishCount()) && orderOneDTO.getFinishCount() != null){
            orderVo.setFinishCount(orderOneDTO.getFinishCount());
        }else {
            orderOneDTO.setFinishCount(0);
        }
        // 3. 判断完成订单的金额
        if (!StringUtils.isEmpty(orderOneDTO.getFinishAmount()) && orderOneDTO.getFinishAmount() != null){
            orderVo.setFinishAmount(orderOneDTO.getFinishAmount());
        }else {
            orderVo.setFinishAmount(new BigDecimal(0));
        }
        // 4. 判断取消订单的数量
        if (!StringUtils.isEmpty(twoDTO.getCancelCount()) && twoDTO.getCancelCount() != null){
            orderVo.setCancelCount(twoDTO.getCancelCount());
        }else {
            orderVo.setCancelCount(0);
        }
        // 5. 判断取消订单的金额
        if (!StringUtils.isEmpty(twoDTO.getCancelAmount()) && twoDTO.getCancelAmount() != null){
            orderVo.setCancelAmount(twoDTO.getCancelAmount());
        }else {
            orderVo.setCancelAmount(new BigDecimal(0));
        }
//        orderVo.setCutomerCount(zeroDTO.getCutomerCount());
//        orderVo.setFinishCount(orderOneDTO.getFinishCount());
//        orderVo.setFinishAmount(orderOneDTO.getFinishAmount());
//        orderVo.setCancelCount(twoDTO.getCancelCount());
//        orderVo.setCancelAmount(twoDTO.getCancelAmount());
        return orderVo;
    }
}