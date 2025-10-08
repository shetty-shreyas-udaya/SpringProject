package com.example.User.Utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "SHETTYSECRET";
    private final long EXP_MS = 24*60*60*1000;

    //Generate tokens with username as subject
    public String generateTokens(String userName)
    {
        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + EXP_MS))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    //extract userName from token
    public String extractUserName(String token)
    {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
