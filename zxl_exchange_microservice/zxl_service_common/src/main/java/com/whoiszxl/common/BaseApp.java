package com.whoiszxl.common;

import com.whoiszxl.base.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.whoiszxl.base"})//扫描base包下的类
@ComponentScan(basePackages={"com.whoiszxl.common"})//扫描本项目下的所有类
public class BaseApp {

    public static void main(String[] args) {
        SpringApplication.run(BaseApp.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
