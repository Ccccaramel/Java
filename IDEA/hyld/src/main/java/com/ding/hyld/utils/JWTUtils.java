package com.ding.hyld.utils;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    /**
     * 过期时间30天
     */
    private static final long EXPIRE_TIME= 30*24*60*60*1000L;
    /**
     * 加密密钥
     */
    private static final String jwtToken = "s211506164";

    /**
     * 生成token
     * @param userId    用户id
     * @return
     */
    public static String createToken(Long userId){
        Map<String,Object> claims = new HashMap();
        claims.put("userId",userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,jwtToken)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME));
        return jwtBuilder.compact();
    }

    /**
     * 验证token是否有效
     * @param token  请求头中携带的token
     * @return  token验证结果  2-token过期；1-token认证通过；0-token认证失败
     */
    public static Map<String, Object> checkToken(String token){
        System.out.println("checkToken:"+token);
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String,Object>)parse.getBody();
        }catch (ExpiredJwtException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String token = JWTUtils.createToken(100L);
        System.out.println(token);
        Map<String,Object> map=JWTUtils.checkToken(token);
        System.out.println(map.get("userId"));
    }
}

