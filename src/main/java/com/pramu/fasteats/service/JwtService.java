package com.pramu.fasteats.service;

import com.pramu.fasteats.config.AuthConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtService {

    //    private static final String SECRET_KEY = "eR6ahCwOdQOCK6DMfENPw7eZBMCknueNre9WQ5+mmfo=";
//    @Value("${application.security.jwt.secret-key}")
//    private String SECRET_KEY;
//    @Value("${application.security.jwt.expiration}")
//    private long jwtExpiration;
//    @Value("${application.security.jwt.refresh-token.expiration}")
//    private long refreshExpiration;

//    private SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private final SecretKey secretKey = Keys.hmacShaKeyFor(AuthConstant.SECRET_KEY.getBytes()) ;

//    @PostConstruct
//    public void init() {
//        this.secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }

    public String buildToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);
        
        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("authorities", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 70000000))
                .signWith(secretKey)
                .compact();
    }

    public String getEmailFromToken(String token) {

        token = token.substring(7);
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return String.valueOf(claims.getSubject());
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();

        for (GrantedAuthority authority : authorities) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

}
