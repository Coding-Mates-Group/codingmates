package com.gbc.codingmates.service.member;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.OAuth;
import com.gbc.codingmates.domain.member.OAuthRepository;
import com.gbc.codingmates.domain.member.OAuthToken;
import com.gbc.codingmates.domain.member.OAuthTokenRepository;
import com.gbc.codingmates.domain.skill.MemberSkill;
import com.gbc.codingmates.domain.skill.MemberSkillRepository;
import com.gbc.codingmates.domain.skill.Skill;
import com.gbc.codingmates.domain.skill.SkillRepository;
import com.gbc.codingmates.dto.MemberDto;
import com.gbc.codingmates.dto.form.MemberJoinDto;
import com.gbc.codingmates.jwt.TokenProvider;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final OAuthTokenRepository oAuthTokenRepository;
    private final OAuthRepository oAuthRepository;
    private final SkillRepository skillRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseEntity join(MemberJoinDto memberJoinDto) {
        final OAuthToken oauthToken = oAuthTokenRepository.findByIdWithLock(
                memberJoinDto.getToken())
            .orElseThrow(() -> new IllegalArgumentException("regeist with invalid token"));

        final Member member = Member.from(memberJoinDto);

        final OAuth oauth = OAuth.of(oauthToken, member);

        List<Skill> memberSkills =
            skillRepository.findAllById(memberJoinDto.getSkillIds());

        if (memberSkills.size() != memberJoinDto.getSkillIds().size()) {
            throw new IllegalArgumentException("invalid input of member skills");
        }

        oAuthRepository.save(oauth);
        oAuthTokenRepository.delete(oauthToken);

        memberSkillRepository.saveAll(
            memberSkills
                .stream()
                .map(skill -> new MemberSkill(member, skill))
                .collect(Collectors.toList())
        );

        return ResponseEntity.ok(tokenProvider.getTokenByUserInfo(
            MemberDto.from(member)
        ));
    }
}
