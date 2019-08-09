package com.whoiszxl.user;

import com.whoiszxl.base.jwt.JwtUtils;
import com.whoiszxl.base.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description: 用户模块启动类
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
@SpringBootApplication
@ComponentScan(basePackages={"com.whoiszxl.base"})//扫描base包下的类
@ComponentScan(basePackages={"com.whoiszxl.user"})//扫描本项目下的所有类
public class UserApp {

    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
}

