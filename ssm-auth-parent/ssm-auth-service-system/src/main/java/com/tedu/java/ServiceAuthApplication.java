package com.tedu.java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author： zyy
 * @date： 2022/10/29 10:50
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.tedu.java.dao")
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class,args);
    }
}
