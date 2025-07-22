package com.yy.utils;

import com.yy.config.JwtProperties;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtUtils {
    @Resource
    private JwtProperties jwtProperties;

    // 生成令牌（含角色信息）
    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
            .setSubject(username)
            .claim("roles", roles)  // 嵌入角色信息
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getTtl()))
            .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes()))
            .compact();
    }

    // 解析令牌获取用户名
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes()))
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    // 验证令牌有效性
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes())).build().parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("token无效",e);
            return false;
        }
    }
}