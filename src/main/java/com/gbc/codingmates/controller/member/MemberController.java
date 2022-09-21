package com.gbc.codingmates.controller.member;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.dto.MemberDto;
import com.gbc.codingmates.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Member> register(@Valid @RequestBody MemberDto memberDTO){
        return ResponseEntity.ok(memberService.register(memberDTO));
    }
}
