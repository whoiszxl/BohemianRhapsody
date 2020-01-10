package com.whoiszxl.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description: 钱包启动类
 * @author: whoiszxl
 * @create: 2019-08-15
 **/
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableAsync //开启异步调用
@ComponentScan(basePackages={"com.whoiszxl.core"})//扫描core包下的类
@ComponentScan(basePackages={"com.whoiszxl.trade"})//扫描本项目下的所有类
@EntityScan(basePackages = {"com.whoiszxl.core.entity"})
public class TradeApp {

    public static void main(String[] args) {
        SpringApplication.run(TradeApp.class, args);
    }
}
