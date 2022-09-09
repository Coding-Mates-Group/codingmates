package com.gbc.codingmates.controller.auth;

import com.gbc.codingmates.api.oAuth.google.GoogleAuthService;
import com.gbc.codingmates.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final GoogleAuthService googleAuthService;

    @ResponseBody
    @GetMapping("/login/oauth2/code/google")
    public String googleAuth(@RequestParam("code") String code) {
        MemberDTO memberDTO = googleAuthService.getMemberInfoByGoogleToken(code);
        return memberDTO.getUsername();
    }

    @GetMapping("/login/oauth2/google")
    public String googleLoginPage() {
        return "redirect:" + googleAuthService.getGoogleLoginFormURI();
    }
}
