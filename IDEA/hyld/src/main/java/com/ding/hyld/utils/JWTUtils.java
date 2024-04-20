package com.ding.hyld.utils;

import com.ding.hyld.constant.CommonCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.LocalTime.now;

@Slf4j
public class JWTUtils {
    public static String getUUID(){
        String token = UUID.randomUUID().toString().replace("-","");
        return token;
    }

    public static SecretKey generaKey(){
        byte[] encodeKey = Base64.getDecoder().decode(CommonCode.SLAT);
        return new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
    }

    /**
     * 生成 token/jwt
     * @param userId    用户id
     * @return
     */
    public static String createToken(Long userId){
        Map<String,Object> claims = new HashMap();
        claims.put("userId",userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, CommonCode.JWT_TOKEN)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+CommonCode.EXPIRE_TIME));
        return jwtBuilder.compact();
    }

    /**
     * 验证token是否有效
     * @param token  请求头中携带的token
     * @return  token验证结果  2-token过期；1-token认证通过；0-token认证失败
     */
    public static Map<String,Object> checkToken(String token){
        Map<String,Object> res = null;
        if(token==null){
            return null;
        }
        try {
            // token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            Jwt parse = Jwts.parser().setSigningKey(CommonCode.JWT_TOKEN).parse(token);
            res = (Map<String, Object>) parse.getBody();
        }catch (ExpiredJwtException e){
            e.printStackTrace();
        }finally {
            return res;
        }
    }

    public static void main(String[] args) {
        fun(100L);
        fun(100L);
    }
    private static void fun(Long value){
        String token = JWTUtils.createToken(value);
        log.info("token:{}",token);
        Map<String,Object> objectMap=JWTUtils.checkToken(token);
        log.info("userId:{}",objectMap.get("userId"));
    }

}

