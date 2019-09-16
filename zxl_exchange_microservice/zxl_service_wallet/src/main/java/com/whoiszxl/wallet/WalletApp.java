package com.whoiszxl.wallet;

import com.whoiszxl.base.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @description: 钱包启动类
 * @author: whoiszxl
 * @create: 2019-08-15
 **/
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages={"com.whoiszxl.base", "com.whoiszxl.wallet.base"})//扫描base包和wallet base下的类
@ComponentScan(basePackages={"com.whoiszxl.wallet"})//扫描本项目下的所有类
@EntityScan(basePackages = {"com.whoiszxl.wallet.base.pojo"})
@EnableJpaRepositories(basePackages = {"com.whoiszxl.wallet.base.dao"})
public class WalletApp {

    public static void main(String[] args) {
        SpringApplication.run(WalletApp.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
