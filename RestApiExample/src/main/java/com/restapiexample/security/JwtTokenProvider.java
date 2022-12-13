package com.restapiexample.security;

import com.restapiexample.Exception.ApiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication){
        String email=authentication.getName();
        Date currentDate=new Date();
        Date expireTime=new Date(currentDate.getTime()+3600000);//ms

        String token= Jwts.builder().setSubject(email).setIssuedAt(currentDate).setExpiration(expireTime).signWith(SignatureAlgorithm.HS256,"JwtSecretKey").compact();
        return token;
    }

    public String getSubject(String token){
        Claims claims=Jwts.parser().setSigningKey("JwtSecretKey").parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean isValidToken(String token){
        try{
            Jwts.parser().setSigningKey("JwtSecretKey").parseClaimsJws(token);
            return true;

        }catch(Exception e){
            throw new ApiException(e.getMessage());

        }
    }
}
