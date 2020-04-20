package com.mi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @Desc: 服务名称
 */
@Data
@Table(name = "appoint_item")
public class AppointItem  implements Serializable {

    /**服务id**/
    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private String itemId;

    /**服务名称**/
    private String itemName;

    /**服务状态   0 正常 1 关闭**/
    private Integer itemStatus;

    /**服务描述**/
    private String itemDesc;

    /**创建时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**修改时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

}