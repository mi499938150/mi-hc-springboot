package com.mi.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2020/4/29
 * @Desc:
 */
@Data
public class AppointOrderCount implements Serializable {

    private Integer count;

    private Double orderAmount;
}