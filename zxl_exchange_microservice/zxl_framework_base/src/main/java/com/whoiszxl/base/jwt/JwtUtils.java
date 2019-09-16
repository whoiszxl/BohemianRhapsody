package com.whoiszxl.base.jwt;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.entity.StatusCode;
import com.whoiszxl.base.enums.role.UserRoleEnum;
import com.whoiszxl.base.exception.ExceptionCast;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @description: jwt工具
 * @author: whoiszxl
 * @create: 2019-08-09
 **/
@Data
@Component
@ConfigurationProperties("jwt.config")
public class JwtUtils {

    private String key;

    private long ttl;

    /**
     * 创建jwt
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJWT(String id, String subject, String roles) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject) //jwt面向的用户
                .setIssuedAt(now) //设置签发时间
                .signWith(SignatureAlgorithm.HS256, key) //设置签名方式
                .claim("roles", roles); //设置角色
        if(ttl > 0) {
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr){
        return  Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }


    public static Claims getClaims(UserRoleEnum userRoleEnum, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Claims claims = (Claims) request.getAttribute(userRoleEnum.getRoleAttrKey());
        if(claims == null) {
            ExceptionCast.castNormalEx(Result.buildError(StatusCode.ACCESSERROR, "您没有权限访问"));
        }
        return claims;
    }

    public static Claims getUserClaims(HttpServletRequest request) {
        return getClaims(UserRoleEnum.ROLE_USER, request);
    }

    public static  Claims getAdminClaims(HttpServletRequest request) {
        return getClaims(UserRoleEnum.ROLE_ADMIN, request);
    }
}
