package com.whoiszxl.xwallet.ethereum.core.config;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.whoiszxl.xwallet.ethereum.core.service.EtherscanApi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-19
 **/
@Configuration
public class EthConfig {

    @Value("${wallet.eth.rpcUrl}")
    private String rpcUrl;

    public Web3j web3j() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        OkHttpClient httpClient = builder.build();
        return Web3j.build(new HttpService(rpcUrl, httpClient, false));
    }

    @Bean
    @ConfigurationProperties(prefix = "etherscan")
    public EtherscanApi etherscanApi(){
        EtherscanApi api = new EtherscanApi();
        return api;
    }

    @Bean
    public JsonRpcHttpClient jsonrpcClient() throws MalformedURLException {
        System.out.println("init jsonRpcClient");
        JsonRpcHttpClient jsonrpcClient = new JsonRpcHttpClient(new URL(rpcUrl));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        jsonrpcClient.setHeaders(headers);
        return jsonrpcClient;
    }
}
