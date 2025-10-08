package com.example.task.Util;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class jwtUtil {

    private final String SECRET = "SHETTYSECRET";

    public String extractUserName(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
