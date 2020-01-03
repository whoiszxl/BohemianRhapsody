package com.whoiszxl.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: rabbitMQ工具类
 * @author: whoiszxl
 * @create: 2020-01-03
 **/
public class RabbitMQUtils {

    public static final String SMS_KEY = "SMS";

    /**
     * 构建rabbitMQ发送的消息体
     * @param value
     * @return
     */
    public static Map buildSendParams(String ...value) {
        Map<String, String> map = new HashMap<>();
        for(int i=0; i<value.length; i=i+2) {
            map.put(value[i], value[i+1]);
        }
        return map;
    }
}
