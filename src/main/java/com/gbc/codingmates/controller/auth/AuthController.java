package com.gbc.codingmates.controller.auth;

import com.gbc.codingmates.config.JwtAuthenticationFilter;
import com.gbc.codingmates.config.JwtTokenProvider;
import com.gbc.codingmates.config.UserAuthentication;
import com.gbc.codingmates.dto.LoginDTO;
import com.gbc.codingmates.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//@RequiredArgsConstructor
//@RestController
public class AuthController {
//    private final JwtTokenProvider jwtTokenProvider;
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;

//    @PostMapping("/login")
//    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO){
//        UserAuthentication authenticationToken = new UserAuthentication(loginDTO.getUsername(), loginDTO.getPassword());
//
//    }
}
