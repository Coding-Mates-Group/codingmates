package com.gbc.codingmates.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        log.error("Responding with unauthorized error. Message - {}", e.getMessage());

        ErrorCode unAuthorizationCode = (ErrorCode) request.getAttribute("unauthorization.code");

        request.setAttribute("response.failure.code", unAuthorizationCode.name());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, unAuthorizationCode.message());
    }
}
