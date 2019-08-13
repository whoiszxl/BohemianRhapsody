package com.whoiszxl.market.config;

import com.whoiszxl.market.handler.MarketHandler;
import com.whoiszxl.market.interceptor.MarketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @description: socket配置类
 * @author: whoiszxl
 * @create: 2019-08-13
 **/
@Configuration
@EnableWebSocket
public class MarketSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MarketInterceptor marketInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(marketHandler(), "/ws").setAllowedOrigins("*")
        .addInterceptors(this.marketInterceptor);
    }


    @Bean
    public WebSocketHandler marketHandler() {
        return new MarketHandler();
    }
}
