package com.whoiszxl.btc.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: bitcoin feign 配置
 * @author: whoiszxl
 * @create: 2020-01-19
 **/
@Configuration
public class BitcoinFeignConfig {

    @Value("${feign.client.rpc.username}")
    private String rpcUsername;

    @Value("${feign.client.rpc.password}")
    private String rpcPassword;

    @Value("${feign.client.rpc.url}")
    private String url;


    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(rpcUsername, rpcPassword);
    }
}
