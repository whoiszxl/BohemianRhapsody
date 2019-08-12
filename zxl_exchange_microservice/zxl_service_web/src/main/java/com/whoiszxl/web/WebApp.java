package com.whoiszxl.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @description: Web网关
 * @author: whoiszxl
 * @create: 2019-08-12
 **/
@EnableZuulProxy
@SpringBootApplication
public class WebApp {
    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
}
