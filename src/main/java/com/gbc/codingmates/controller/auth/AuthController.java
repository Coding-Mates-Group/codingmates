package com.gbc.codingmates.controller.auth;

import com.gbc.codingmates.api.oAuth.AuthService;
import com.gbc.codingmates.dto.LoginDto;
import com.gbc.codingmates.dto.MemberDto;
import com.gbc.codingmates.dto.TokenDto;
import com.gbc.codingmates.jwt.JwtFilter;
import com.gbc.codingmates.jwt.TokenProvider;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/login/oauth2/code/google")
    public String googleAuth(@RequestParam("code") String code) {
        MemberDto memberDTO = authService.getMemberInfoByGoogleCode(code);
        return memberDTO.getUsername();
    }

    @GetMapping("/login/oauth2/google")
    public ResponseEntity googleLoginPage() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(authService.getGoogleLoginURI()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/login/oauth2/github")
    public ResponseEntity githubLoginPage() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(authService.getGithubLoginURI()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @ResponseBody
    @GetMapping("/login/oauth2/code/github")
    public String githubAuth(@RequestParam("code") String code) {
        MemberDto memberDTO = authService.getMemberInfoByGithubCode(code);
        return memberDTO.getUsername();
    }
}
