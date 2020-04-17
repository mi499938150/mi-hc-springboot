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
 * @Desc: 预约分类
 */
@Data
@Table(name = "appoint_category")
public class AppointCategory implements Serializable {


    /**分类id**/
    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private Integer categoryId;

    /**分类名称**/
    private String categoryName;

    /**分类图标**/
    private String category_icon;

    /**创建时间**/
    private Date createTime;

    /**修改时间**/
    private Date updateTime;

}