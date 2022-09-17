package com.gbc.codingmates.controller.auth;

import com.gbc.codingmates.api.oAuth.AuthService;
import com.gbc.codingmates.domain.member.OAuth;
import com.gbc.codingmates.domain.member.OAuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/oauth2/code/google")
    public ResponseEntity googleAuth(@RequestParam("code") String code) {
        return authService.authorizationByAuthCode(code, OAuthType.GOOGLE);
    }

    @GetMapping("/oauth2/google")
    private ResponseEntity googleLoginPage() {
        return authService.getLoginURI(OAuthType.GOOGLE);
    }

    @GetMapping("/oauth2/github")
    private ResponseEntity githubLoginPage() {
        return authService.getLoginURI(OAuthType.GITHUB);
    }

    @ResponseBody
    @GetMapping("/oauth2/code/github")
    public ResponseEntity githubAuth(@RequestParam("code") String code) {
        return authService.authorizationByAuthCode(code, OAuthType.GITHUB);
    }

    @GetMapping("/oauth2/facebook")
    public ResponseEntity facebookLoginPage() {
        return authService.getLoginURI(OAuthType.FACEBOOK);
    }

    @ResponseBody
    @GetMapping("/oauth2/code/facebook")
    public ResponseEntity facebookAuth(@RequestParam("code") String code) {
        return authService.authorizationByAuthCode(code, OAuthType.FACEBOOK);
    }
}
