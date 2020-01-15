package com.whoiszxl.usercenter;

import com.whoiszxl.core.jwt.JwtUtils;
import com.whoiszxl.core.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-09
 **/
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages={"com.whoiszxl.core"})//扫描core包下的类
@ComponentScan(basePackages={"com.whoiszxl.usercenter"})//扫描本项目下的所有类
@ComponentScan(basePackages={"com.whoiszxl.xwallet.core.client"})//扫描远程wallet feign
@EntityScan(basePackages = {"com.whoiszxl.core.entity"})
@EnableJpaRepositories(basePackages = "com.whoiszxl.core")
public class UserCenterApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

}
