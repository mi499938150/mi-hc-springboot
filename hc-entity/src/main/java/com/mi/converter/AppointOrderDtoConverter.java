package com.mi.converter;

import com.mi.dto.OrderDTO;
import com.mi.entity.AppointOrder;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换器
 */
public class AppointOrderDtoConverter {

    public static OrderDTO convert(AppointOrder appointOrder){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(appointOrder,orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> convert(List<AppointOrder> appointOrder){
      return   appointOrder.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
