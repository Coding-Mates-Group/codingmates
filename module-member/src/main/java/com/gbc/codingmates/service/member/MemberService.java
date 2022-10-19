package com.gbc.codingmates.service.member;

import com.gbc.codingmates.domain.member.OAuthRepository;
import com.gbc.codingmates.dto.MemberAliasCheck;
import com.gbc.codingmates.dto.form.MemberJoinDto;
import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.MemberRepository;
import com.gbc.codingmates.domain.member.OAuth;
import com.gbc.codingmates.domain.member.OAuthToken;
import com.gbc.codingmates.domain.member.OAuthTokenRepository;
import com.gbc.codingmates.event.MemberCreateEvent;
import com.gbc.codingmates.jwt.TokenProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final ApplicationEventPublisher eventPublisher;
    private final OAuthTokenRepository oAuthTokenRepository;
    private final OAuthRepository oAuthRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseEntity join(MemberJoinDto memberJoinDto) {
        final OAuthToken oauthToken = oAuthTokenRepository.findByIdWithLock(
                memberJoinDto.getToken())
            .orElseThrow(() -> new IllegalArgumentException("regeist with invalid token"));

        final Member member = Member.from(memberJoinDto);

        final OAuth oauth = OAuth.of(oauthToken, member);

        oAuthRepository.save(oauth);
        oAuthTokenRepository.delete(oauthToken);

        MemberCreateEvent memberCreateEvent = new MemberCreateEvent("MEMBER_CREATE", member.getId(),
            memberJoinDto.getSkillIds());
        eventPublisher.publishEvent(memberCreateEvent);

        ;
        return ResponseEntity.ok(tokenProvider.getTokenByUserInfo(
            Member.getMemberDto(member)
        ));
    }

    public ResponseEntity checkAlias(MemberAliasCheck memberAliasCheck) {
        Optional<Member> member = memberRepository.findByUsername(
            memberAliasCheck.getUserAlias());
        if (member.isPresent()) {
            return ResponseEntity.ok("duplicated");
        }
        return ResponseEntity.ok("available");
    }
}
