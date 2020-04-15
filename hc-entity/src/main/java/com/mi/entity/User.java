package com.mi.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Rong
 * @date : 2020/4/15
 * @Desc:
 */
@Data
public class User implements Serializable {
    private String uid;

    private String username;

    private String passwrod;
}