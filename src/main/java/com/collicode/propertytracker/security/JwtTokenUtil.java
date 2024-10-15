package com.collicode.propertytracker.security;


import static com.collicode.propertytracker.config.Constants.ACCESS_TOKEN_VALIDITY_REMEMBER_ME_SECONDS;
import static com.collicode.propertytracker.config.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.collicode.propertytracker.config.Constants.SIGNING_KEY;

import com.collicode.propertytracker.service.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class JwtTokenUtil implements Serializable {
    public String getUsernameFromToken(String token) {
        log.info("Getting Username from Token!");
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        if (claims==null){
            return null;
        }
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        try{
            return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e){
            log.error("{}", e.getMessage());
            return null;
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDTO user, Boolean rememberMe) {
        return doGenerateToken(user, rememberMe);
    }

    private String doGenerateToken(UserDTO user, Boolean rememberMe) {

        final Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority(user.getUserRole().name())));
        claims.put("bid", user.getAgent() != null ? user.getAgent().getAgentCode(): null);

        long tokenValidityPeriod= ACCESS_TOKEN_VALIDITY_SECONDS;
        if (rememberMe != null && rememberMe){
            tokenValidityPeriod = ACCESS_TOKEN_VALIDITY_REMEMBER_ME_SECONDS;
        }

        return Jwts.builder().setClaims(claims).setIssuer("https://natujenge.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityPeriod * 1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        log.info("Token validate for {}",username);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    public String getUsernameUnlimitedSkew(String token) {
        String username=null;
        try {
            username = Jwts.parser().setSigningKey(SIGNING_KEY).setAllowedClockSkewSeconds(Integer.MAX_VALUE)
                    .parseClaimsJws(token).getBody().getSubject();
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
        return username;
    }
}
