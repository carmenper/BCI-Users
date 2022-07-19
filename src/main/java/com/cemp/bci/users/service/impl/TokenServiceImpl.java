package com.cemp.bci.users.service.impl;

import com.cemp.bci.users.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${security.jw" +
            "t.secret}")
    private String jwtSecret;

    @Override
    public String refreshToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, email);
    }

    @Override
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(getKey()).build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private String generateToken(Map<String, Object> claims, String email) {
        return Jwts.builder().setClaims(claims).setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getKey(), SignatureAlgorithm.HS512).compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

}
