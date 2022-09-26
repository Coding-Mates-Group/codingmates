package com.gbc.codingmates.service.member;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.MemberStatus;
import com.gbc.codingmates.domain.member.OAuth;
import com.gbc.codingmates.domain.member.OAuthRepository;
import com.gbc.codingmates.domain.member.OAuthToken;
import com.gbc.codingmates.domain.member.OAuthTokenRepository;
import com.gbc.codingmates.dto.MemberDto;
import com.gbc.codingmates.dto.form.MemberJoinDto;
import com.gbc.codingmates.jwt.TokenProvider;
import com.gbc.codingmates.util.FileHandler;
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
    private final FileHandler fileHandler;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseEntity join(MemberJoinDto memberJoinDto) {
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

        oAuthRepository.save(oauth);
        oAuthTokenRepository.delete(oauthToken);

        if (memberJoinDto.profileImageExist()) {
            String profileRelativePath = fileHandler.saveProfileImage(
                memberJoinDto.getProfileImage(),
                member.getId());
            member.mapMemberProfileImagePath(profileRelativePath);
        }

        return ResponseEntity.ok(tokenProvider.getTokenByUserInfo(
            MemberDto.from(member)
        ));
    }
}
