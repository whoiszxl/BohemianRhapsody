package com.whoiszxl.btc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: bitcoin启动类
 * @author: whoiszxl
 * @create: 2019-09-02
 **/
@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration
public class BitcoinApp {

    public static void main(String[] args) {
        SpringApplication.run(BitcoinApp.class, args);
    }
}
