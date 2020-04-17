package com.mi.form;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc:
 */
@Data
public class AppointItemForm {

    private String itemId;

    /**服务名称**/
    private String itemName;

    /**服务状态   0 正常 1 关闭**/
    private Integer itemStatus;

    /**服务描述**/
    private String itemDesc;

    /**创建时间**/
    private Timestamp createTime;

    /**修改时间**/
    private Timestamp updateTime;
}