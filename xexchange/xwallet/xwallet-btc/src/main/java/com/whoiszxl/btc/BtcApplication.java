package com.whoiszxl.btc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @description: bitcoin启动类
 * @author: whoiszxl
 * @create: 2020-01-19
 **/
@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.whoiszxl.core", "com.whoiszxl.xwallet.core", "com.whoiszxl.btc"})//扫描base包和wallet base下的类
@EntityScan(basePackages = {"com.whoiszxl.xwallet.core.entity", "com.whoiszxl.core.entity"})
public class BtcApplication {


    public static void main(String[] args) {
        SpringApplication.run(BtcApplication.class, args);
    }
}
