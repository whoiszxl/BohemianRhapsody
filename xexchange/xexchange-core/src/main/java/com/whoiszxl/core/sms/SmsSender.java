package com.whoiszxl.core.sms;

import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.enums.redis.UserRedisPrefixEnum;
import com.whoiszxl.core.utils.RabbitMQUtils;
import com.whoiszxl.core.utils.RandomUtils;
import com.whoiszxl.core.utils.RedisUtils;
import com.whoiszxl.core.utils.RegexUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: 短信发动类
 * @author: whoiszxl
 * @create: 2020-01-09
 **/
@Component
public class SmsSender {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${msg.mock}")
    private boolean msgMock;

    /**
     * 发送短信验证码
     * @param mobile 手机号码
     */
    public Result sendVerifySms(String mobile) {
        //对手机号进行校验
        if(!RegexUtils.checkPhone(mobile)) {
            return Result.buildError();
        }
        //生成验证码
        String code = RandomUtils.generateNumberString(6);
        //存入redis
        redisUtils.setEx(UserRedisPrefixEnum.USER_REGISTER_PHONE_CODE.getKey() + mobile,
                code,
                UserRedisPrefixEnum.USER_REGISTER_PHONE_CODE.getTime(),
                UserRedisPrefixEnum.USER_REGISTER_PHONE_CODE.getUnit());
        //发送到MQ
        if(!msgMock) {
            rabbitTemplate.convertAndSend(RabbitMQUtils.SMS_KEY, RabbitMQUtils.buildSendParams("mobile", mobile, "code", code));
        }
        return Result.buildSuccess();
    }
}
