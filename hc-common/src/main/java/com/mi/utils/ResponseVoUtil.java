package com.mi.utils;

import com.mi.vo.CommonResponse;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 封装参数显示
 */
public class ResponseVoUtil {

    public static CommonResponse success(Object object) {
             CommonResponse response = new CommonResponse();
             response.setData(object);
             response.setCode(0);
             response.setMessage("成功");
             return response;
    }

    public static CommonResponse success(){
        return success(null);
    }

    public static CommonResponse error(Integer code,String msg){
        CommonResponse response = new CommonResponse();
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }
}