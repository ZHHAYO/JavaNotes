package com.hiyongz.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtUtils {

    private static String JWT_KEY = "hiyongz"; //签名密钥
    private static Long JWT_TTL = 60 * 60 *1000L; // 有效期: 60 * 60 *1000  一个小时

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims) //自定义信息（有效载荷）
                .signWith(SignatureAlgorithm.HS256, JWT_KEY) //签名算法（头部）
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TTL)) //过期时间
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_KEY) //指定签名密钥
                .parseClaimsJws(jwt) //指定令牌Token
                .getBody();
        return claims;
    }
}
