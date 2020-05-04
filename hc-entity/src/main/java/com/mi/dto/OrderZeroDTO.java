package com.mi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2020/4/29
 * @Desc: 根据订单状态和日期查询用户的总数量
 */
@Data
public class OrderZeroDTO implements Serializable{

    /**预约用户数量**/
    private Integer cutomerCount;

}