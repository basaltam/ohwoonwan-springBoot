package com.patrick.ohwoonwan.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    public String create(String userId){

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 현재시간 기준 먼저
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userId).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();
        return jwt;
    }


    //TODO: jwt 검증
    public String validate(String jwt){

        Claims claims = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return claims.getSubject();
    }
}
