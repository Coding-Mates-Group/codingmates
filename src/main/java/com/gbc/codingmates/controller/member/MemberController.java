package com.gbc.codingmates.controller.member;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.MemberRepository;
import com.gbc.codingmates.dto.MemberDTO;
import com.gbc.codingmates.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Member> register(@Valid @RequestBody MemberDTO memberDTO){
        return ResponseEntity.ok(memberService.register(memberDTO));
    }
}
