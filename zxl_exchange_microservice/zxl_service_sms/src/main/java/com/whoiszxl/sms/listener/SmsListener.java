package com.whoiszxl.sms.listener;

import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.whoiszxl.base.utils.RabbitMQUtils;
import com.whoiszxl.sms.sender.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 短信监听类
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
@Slf4j
@Component
@RabbitListener(queues = RabbitMQUtils.SMS_KEY)
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String templateCode;

    @Value("${aliyun.sms.sign_name}")
    private String signName;

    /**
     * 发送短信
     *
     * @param message
     */
    @RabbitHandler
    public void sendSms(Map<String, String> message) {
        try {
            CommonResponse commonResponse = smsUtil.sendSms(message.get("mobile"),
                    templateCode,
                    signName,
                    " {\"code\":\"" + message.get("code") + "\"}");
            log.info("短信发送状态,手机号：{},验证码：{}, 状态：{}", message.get("mobile"), message.get("code"), commonResponse.getData());
        } catch (ClientException e) {
            log.error("短信发送失败", e);
        }
    }

}
