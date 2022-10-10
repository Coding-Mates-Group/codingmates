package com.gbc.codingmates.controller.member;

import com.gbc.codingmates.dto.MemberAliasCheck;
import com.gbc.codingmates.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/members", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor()
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping(value = "/alias/check")
    public ResponseEntity checkAliasDuplicated(@RequestBody MemberAliasCheck memberAliasCheck) {

        return memberService.checkAlias(memberAliasCheck);
    }
}
