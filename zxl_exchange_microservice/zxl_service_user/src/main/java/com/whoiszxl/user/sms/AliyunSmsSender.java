package com.whoiszxl.user.sms;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.enums.redis.UserRedisPrefixEnum;
import com.whoiszxl.base.utils.RabbitMQUtils;
import com.whoiszxl.base.utils.RandomUtils;
import com.whoiszxl.base.utils.RedisUtils;
import com.whoiszxl.base.utils.RegexUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 阿里云短信发送类
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
@Component
public class AliyunSmsSender {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 发送短信验证码
     * @param mobile 手机号码
     */
    public Result sendSms(String mobile) {
        //对手机号进行校验
        if(!RegexUtils.checkPhone(mobile)) {
            return Result.buildError();
        }
        //生成验证码
        String code = RandomUtils.generateNumberString(6);
        //存入redis
        redisUtils.setEx(UserRedisPrefixEnum.USER_REGISTER_PHONE_CODE.getKey(),
                code,
                UserRedisPrefixEnum.USER_REGISTER_PHONE_CODE.getTime(),
                UserRedisPrefixEnum.USER_REGISTER_PHONE_CODE.getUnit());
        //发送到MQ
        rabbitTemplate.convertAndSend(RabbitMQUtils.SMS_KEY, RabbitMQUtils.buildSendParams("mobile", mobile, "code", code));
        return Result.buildSuccess();
    }

}
