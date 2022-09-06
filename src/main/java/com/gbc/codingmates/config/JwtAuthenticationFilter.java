package com.gbc.codingmates.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    //authenticate token of its validity
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {
        try {
            //retrieve jwt token from http request
            String jwt = getJwtFromRequest(request);
            if (jwt != null && JwtTokenProvider.validateToken(jwt)) {
                //retrieve member id from jwt token
                String userId = JwtTokenProvider.getUserIdFromJWT(jwt);
                //authenticate member id
                UserAuthentication authentication = new UserAuthentication(userId, null, null);
                //set the details
                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));
                //to continue to allow connection via session, save Authentication in securityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                if (jwt == null) {
                    request.setAttribute("Unauthorisation", "401 No Authentication key");
                }

                if (JwtTokenProvider.validateToken(jwt)) {
                    request.setAttribute("Unauthorisation", "401-001 Authentication key expired");
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorisation");
        try {
            if ((bearerToken != null) && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring("Bearer ".length());
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
    }
}

