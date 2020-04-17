package com.mi.exception;

import com.mi.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Rong
 * @date : 2020/4/16
 * @Desc: 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHc {

    /**
     * 处理运行时异常
     * 不使用统一处理注解  @IgnoreResponseAdvice
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = HcException.class)
    public CommonResponse<String> handlerHcException(HttpServletRequest request,
                                                     HcException e){
        log.error("exception:",e);
        CommonResponse<String> response = new CommonResponse<>(-1,"business error");
        response.setData(e.getMessage());
        return response;
    }

    /**
     * 用来捕获404，400这种无法到达controller的错误
     * @param ex
     * @return
     * @throws HcException
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResponse<Object> defaultErrorHandler(Exception ex)throws Exception{
        log.error("",ex);
        CommonResponse<Object> response = new CommonResponse<>();
        response.setMessage(ex.getMessage());
        if (ex instanceof NoHandlerFoundException){
            response.setCode(404);
        } else {
            response.setCode(500);
        }
        response.setData(null);
        return response;
    }

}