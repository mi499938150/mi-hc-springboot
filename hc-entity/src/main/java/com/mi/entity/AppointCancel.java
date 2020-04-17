package com.mi.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 订单取消理由实体类
 */
@Data
@Table(name = "appoint_cancle")
public class AppointCancel implements Serializable{

    /** 取消id**/
    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private String cancelId;

    /**取消理由**/
    private String cancelReason;

    /**创建时间**/
    private Date createTime;

    /**修改时间**/
    private Date updateTime;


}