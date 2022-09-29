package com.gbc.codingmates.service.member;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.MemberStatus;
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
import com.gbc.codingmates.util.FileHandler;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final OAuthTokenRepository oAuthTokenRepository;
    private final OAuthRepository oAuthRepository;
    private final SkillRepository skillRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final FileHandler fileHandler;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseEntity join(MemberJoinDto memberJoinDto, MultipartFile profile) {
        final OAuthToken oauthToken = oAuthTokenRepository.findByIdWithLock(
                memberJoinDto.getToken())
            .orElseThrow(() -> new IllegalArgumentException("regeist with invalid token"));

        final Member member = Member.builder()
            .username(memberJoinDto.getUserAlias())
            .memberStatus(MemberStatus.BASIC)
            .build();

        final OAuth oauth = OAuth.builder()
            .authId(oauthToken.getAuthUserId())
            .member(member)
            .provider(oauthToken.getOAuthType())
            .build();

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

        if (!isEmpty(profile)) {
            String profileRelativePath = fileHandler.saveProfileImage(
                profile,
                member.getId());
            member.mapMemberProfileImagePath(profileRelativePath);
        }

        return ResponseEntity.ok(tokenProvider.getTokenByUserInfo(
            MemberDto.from(member)
        ));
    }
}
