package com.whoiszxl.btc;

import com.whoiszxl.base.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: bitcoin启动类
 * @author: whoiszxl
 * @create: 2019-09-02
 **/
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.whoiszxl.wallet.base.dao"})
@ComponentScan(basePackages={"com.whoiszxl.base", "com.whoiszxl.wallet.base", "com.whoiszxl.btc"})//扫描base包和wallet base下的类
@EntityScan(basePackages = {"com.whoiszxl.wallet.base.pojo"})
public class BitcoinApp {

    public static void main(String[] args) {
        SpringApplication.run(BitcoinApp.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
