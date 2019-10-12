package com.whoiszxl.common;

import com.whoiszxl.base.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages={"com.whoiszxl.base"})//扫描base包下的类
@ComponentScan(basePackages={"com.whoiszxl.common"})//扫描本项目下的所有类
@EnableJpaRepositories(basePackages = {"com.whoiszxl.common.dao"})
@ComponentScan(basePackages={"com.whoiszxl.base"})//扫描base包和wallet base下的类
@EntityScan(basePackages = {"com.whoiszxl.common.pojo"})
public class CommonApp {

    public static void main(String[] args) {
        SpringApplication.run(CommonApp.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")  //可访问ip，ip最好从配置文件中获取，
                        .allowedMethods("PUT", "DELETE","GET","POST")
                        .allowedHeaders("*")
                        .exposedHeaders("access-control-allow-headers",
                                "access-control-allow-methods",
                                "access-control-allow-origin",
                                "access-control-max-age",
                                "X-Frame-Options")
                        .allowCredentials(false).maxAge(3600);
            }
        };

    }
}
