package com.mi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc:  微信授权暂用实体
 */
@Data
@Table(name = "wechat_user")
public class WeChatUser implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true) //回显
    /**openid**/
    private String openId;

    /**微信名称**/
    private String nickName;

    /**性别描述**/
    private String sexDesc;

    /**性别**/
    private Integer sex;

    /**语言**/
    private String language;

    /**城市**/
    private String city;

    /**省份**/
    private String province;

    /**国家**/
    private String country;

    /**头像**/
    private String headImgUrl;

    /**创建时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**修改时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

}