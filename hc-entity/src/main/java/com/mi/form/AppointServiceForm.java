package com.mi.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Rong
 * @date : 2020/4/21
 * @Desc:
 */

@Data
public class AppointServiceForm {

    /**预约id**/
    private String appointId;

    /**预约名称**/
    private String appointName;

    /**预约价格**/
    private BigDecimal appointPrice;

    /**预约日期**/
//    private Date appointWeek;

    /**预约描述**/
    private String appointDesc;

    /**预约时间**/
    private String appointTime;

    /**开启状态 0正常 1关闭**/
    private Integer appointStatus;

    /**预约状态 0未预约 1已预约**/
    private Integer statusType;

    /**预约分类id**/
    private Integer categoryType;

    /**创建时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**修改时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}