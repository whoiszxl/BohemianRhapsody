package com.whoiszxl.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description: sms启动类
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
@EnableEurekaClient
@SpringBootApplication
public class SmsApp {

    public static void main(String[] args) {
        SpringApplication.run(SmsApp.class, args);
    }
}
