package com.mi.exception;

import com.mi.enums.ResultEnum;

/**
 * @author : Rong
 * @date : 2020/4/26
 * @Desc:
 */
public class HctException extends RuntimeException {

    private Integer code;

    public HctException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public HctException(Integer code,String message){
        super(message);
        this.code = code;
    }
}