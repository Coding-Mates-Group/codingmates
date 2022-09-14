package com.gbc.codingmates.controller.auth;

import com.gbc.codingmates.api.oAuth.AuthService;
import com.gbc.codingmates.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @ResponseBody
    @GetMapping("/login/oauth2/code/google")
    public String googleAuth(@RequestParam("code") String code) {
        MemberDTO memberDTO = authService.getMemberInfoByGoogleCode(code);
        return memberDTO.getUsername();
    }

    @GetMapping("/login/oauth2/google")
    public String googleLoginPage() {
        return "redirect:" + authService.getGoogleLoginURI();
    }

    @GetMapping("/login/oauth2/github")
    public String githubLoginPage() {
        return "redirect:" + authService.getGithubLoginURI();
    }

    @ResponseBody
    @GetMapping("/login/oauth2/code/github")
    public String githubAuth(@RequestParam("code") String code) {
        MemberDTO memberDTO = authService.getMemberInfoByGithubCode(code);
        return memberDTO.getUsername();
    }
}
