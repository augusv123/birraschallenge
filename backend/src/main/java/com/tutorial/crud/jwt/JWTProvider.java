package com.tutorial.crud.jwt;

import com.tutorial.crud.entity.MainUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTProvider {
    private final static Logger logger = LoggerFactory.getLogger(JWTProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        MainUser admin = (MainUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(admin.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() +expiration *1000)).signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();

    }
    public boolean valdiateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        catch (MalformedJwtException e){
            logger.error("token malformado");
        }
        catch (UnsupportedJwtException e){
            logger.error("token no soportado");
        }
        catch (ExpiredJwtException e){
            logger.error("token expirado");
        }
        catch (IllegalArgumentException e){
            logger.error("token vacio");
        }
        catch (SignatureException e){
            logger.error("error de firma");
        }
        return false;
    }
}
