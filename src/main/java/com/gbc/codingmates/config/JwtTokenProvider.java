package com.gbc.codingmates.config;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final String JWT_SECRET = "secretKey";

//     token TTL = 30mins
    private static final long JWT_EXPIRATION_MS = 30*60*1000L;

//    @Value("${jwt.secret}")
//    private static String JWT_SECRET;
//
//    @Value("${jwt.token-validity-in-seconds}")
//    private static long JWT_EXPIRATION_MS;

    // generate jwt token
    public static String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //seting header type as jwt
                .setIssuer("coding_mates") //iss = token issuer
                .setSubject((String) authentication.getPrincipal()) // 사용자
                .setIssuedAt(now) // iat = date at which token is issued
                .setExpiration(expiryDate) // exp = date at which token expires
                .claim("testing member", authentication)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET) // HS512 used for encryption, secret key
                .compact(); //compact to make jwt token
    }

    // getting info out of the Jwt (토큰에서 아이디 추출)
    public static String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // Jwt 토큰 유효성 검사
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - Wrong jwt template");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
