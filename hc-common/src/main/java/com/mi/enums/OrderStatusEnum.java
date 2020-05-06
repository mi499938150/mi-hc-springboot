package com.mi.enums;

import lombok.Getter;

/**
 * @author : Rong
 * @date : 2020/4/24
 * @Desc:
 */
@Getter
public enum OrderStatusEnum  implements CodeEnum{

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}