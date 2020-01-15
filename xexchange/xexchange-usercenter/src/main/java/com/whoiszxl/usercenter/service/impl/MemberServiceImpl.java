package com.whoiszxl.usercenter.service.impl;

import com.google.common.collect.Maps;
import com.whoiszxl.core.dao.MemberDao;
import com.whoiszxl.core.entity.Member;
import com.whoiszxl.core.enums.normal.SwitchStatusEnum;
import com.whoiszxl.core.enums.redis.UserRedisPrefixEnum;
import com.whoiszxl.core.enums.role.MemberRoleEnum;
import com.whoiszxl.core.enums.user.MemberStatusEnum;
import com.whoiszxl.core.jwt.JwtUtils;
import com.whoiszxl.core.utils.RedisUtils;
import com.whoiszxl.core.utils.VoPoConverter;
import com.whoiszxl.usercenter.entity.request.RegisterRequest;
import com.whoiszxl.usercenter.entity.vo.MemberVo;
import com.whoiszxl.usercenter.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @description: 用户服务实现类
 * @author: whoiszxl
 * @create: 2020-01-09
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MemberDao memberDao;


    @Override
    public boolean checkVerifyCode(String mobile, String memberVerifyCode) {
        String redisVerifyCode = redisUtils.get(UserRedisPrefixEnum.USER_REGISTER_PHONE_CODE.getKey() + mobile);
        return StringUtils.equals(memberVerifyCode, redisVerifyCode);
    }

    @Override
    public void registerToDb(RegisterRequest registerRequest) {
        Member member = (Member) new Member()
                .setUsername(registerRequest.getMobile())
                .setPassword(encoder.encode(registerRequest.getPassword()))
                .setPhone(registerRequest.getMobile())
                .setLoginCount(0)
                .setTradeFeeRate(new BigDecimal("0.001"))
                .setGradeLevel("1")
                .setDayWithdrawCount(1)
                .setStatus(MemberStatusEnum.MEMBER_VAILD.getStatus())
                .setUpdatedAt(new Date())
                .setCreatedAt(new Date());
        memberDao.save(member);
    }

    @Override
    public Member login(String phone, String password) {
        Member member = memberDao.findMemberByPhoneAndStatus(phone, MemberStatusEnum.MEMBER_VAILD.getStatus());
        if(member != null && encoder.matches(password, member.getPassword())) {
            //更新最后登录时间
            member.setLastLogin(new Date());
            memberDao.save(member);
            return member;
        }
        return null;
    }

    @Override
    public Map<String, Object> issueSign(Member member, String roleName) {
        String token = jwtUtils.createJWT(member.getId().toString(), member.getUsername(), MemberRoleEnum.ROLE_USER.getRoleName());
        Map<String, Object> map = Maps.newHashMap();
        map.put("token", token);
        map.put("username", member.getUsername());
        map.put("countryCode", member.getCountryCode());
        return map;
    }

    @Override
    public void removeVerifyInRedis(String mobile) {
        redisUtils.delete(UserRedisPrefixEnum.USER_REGISTER_PHONE_CODE.getKey() + mobile);
    }

    @Override
    public MemberVo getMemberInfoByMemberId(Long memberId) {
        Member member = memberDao.findMemberByIdAndStatus(memberId, SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        if(member != null) {
            return VoPoConverter.copyProperties(member, MemberVo.class);
        }
        return null;
    }
}
