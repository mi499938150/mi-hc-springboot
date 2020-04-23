package com.mi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author : Rong
 * @date : 2020/4/15
 * @Desc: Web 启动类
 */

@SpringBootApplication
@MapperScan("com.mi.mapper")
public class WebApplication {
    public static void main(String args[]){
        SpringApplication.run(WebApplication.class,args);
    }
}