package com.pramu.fasteats.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

import static com.pramu.fasteats.config.AuthConstant.SECRET_KEY;

public class JwtAuthFilter extends OncePerRequestFilter {

//    @Value("${application.security.jwt.secret-key}")
//    private String SECRET_KEY;
//
//    private SecretKey secretKey;
//
//    @PostConstruct
//    public void init() {
//        this.secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader("Authorization");

        if(jwt!=null){

            jwt = jwt.substring(7);

            try {
                  SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//                SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();

                String email = claims.getSubject();
                String authorities = String.valueOf(claims.get("authorities"));

                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception e){
                throw new BadCredentialsException("Token invalid !" + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

}
