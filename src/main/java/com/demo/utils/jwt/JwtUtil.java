package com.demo.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SIGN_KEY = "SomersaultCloud";
    //    private static final Long EXPIRE = 43200000L; //12h
    private static final Long EXPIRE = 2592000000L; //TODO 待改 jwt有效时间

    /**
     * 防止实例化
     */
    private JwtUtil(){}

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return 用户token
     */
    public static String createJwt(Map<String, Object> claims){
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY.getBytes())  //go的jwt数字签名不支持字符串
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return claims内容的键值对
     * Key:    userId  用户ID
     */
    public static Map<String,Object> parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(SIGN_KEY.getBytes())
                .parseClaimsJws(jwt)
                .getBody();
        return new HashMap<>(claims);
    }
}