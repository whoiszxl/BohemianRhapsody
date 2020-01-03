package com.whoiszxl.sms.listener;

import com.aliyun.oss.ClientException;
import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.utils.RabbitMQUtils;
import com.whoiszxl.sms.provider.SMSProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 短信监听发送
 * @author: whoiszxl
 * @create: 2020-01-03
 **/
@Slf4j
@Component
@RabbitListener(queues = RabbitMQUtils.SMS_KEY)
public class SmsListener {

    @Autowired
    private SMSProvider aliyunSMSProvider;

    /**
     * 发送短信
     *
     * @param message
     */
    @RabbitHandler
    public void sendSms(Map<String, String> message) {
        try {
            Result result = aliyunSMSProvider.sendVerifyMessage(message.get("mobile"), message.get("code"));
            log.info("发送一条短信验证码,{}", result.getData().toString());
        } catch (ClientException e) {
            log.error("短信发送失败", e);
        }
    }

}
