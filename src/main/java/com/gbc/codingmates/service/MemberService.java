package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.MemberRepository;
import com.gbc.codingmates.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member register(MemberDTO memberDTO){
        Member member = Member.builder()
                .username(memberDTO.getUsername())
                .password(memberDTO.getPassword())
                .build();
        return memberRepository.save(member);
    }
}
