package com.whoiszxl.manager;

import com.whoiszxl.base.jwt.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @description: manager网关启动类
 * @author: whoiszxl
 * @create: 2019-08-12
 **/
@EnableZuulProxy
@SpringBootApplication
public class ManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class, args);
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
}
