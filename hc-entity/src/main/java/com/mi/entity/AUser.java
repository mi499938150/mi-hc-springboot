package com.mi.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2020/4/15
 * @Desc: 后台用户密码登录
 */
@Data
@Table(name = "appoint_user")
public class AUser implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true) //回显
    private Integer uid;

    private String userName;

    private String passWord;

    private String phoneNumber;

}