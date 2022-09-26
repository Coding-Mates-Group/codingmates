package com.gbc.codingmates.controller.member;

import com.gbc.codingmates.dto.form.MemberJoinDto;
import com.gbc.codingmates.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("")
    public ResponseEntity register(@RequestBody @Validated MemberJoinDto memberJoinDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().toString());
        }
        return memberService.join(memberJoinDto);
    }
}
