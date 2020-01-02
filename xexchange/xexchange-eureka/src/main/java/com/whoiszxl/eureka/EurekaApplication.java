package com.whoiszxl.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description: Eureka注册中心
 * @author: whoiszxl
 * @create: 2020-01-02
 **/
@SpringBootApplication //表明是一个SpringBoot项目
@EnableEurekaServer //打开eureka server服务
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class);
    }
}
