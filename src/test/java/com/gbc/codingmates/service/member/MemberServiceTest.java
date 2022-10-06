package com.gbc.codingmates.service.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.gbc.codingmates.domain.member.OAuthRepository;
import com.gbc.codingmates.domain.member.OAuthToken;
import com.gbc.codingmates.domain.member.OAuthTokenRepository;
import com.gbc.codingmates.domain.member.OAuthType;
import com.gbc.codingmates.domain.skill.MemberSkillRepository;
import com.gbc.codingmates.domain.skill.Skill;
import com.gbc.codingmates.domain.skill.SkillRepository;
import com.gbc.codingmates.dto.form.MemberJoinDto;
import com.gbc.codingmates.jwt.TokenProvider;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private MemberService memberService;
    @Mock
    private OAuthTokenRepository oAuthTokenRepository;
    @Mock
    private OAuthRepository oAuthRepository;
    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private SkillRepository skillRepository;
    @Mock
    private MemberSkillRepository memberSkillRepository;

    @BeforeEach
    public void init() {
        memberService = new MemberService(oAuthTokenRepository, oAuthRepository, skillRepository,
            memberSkillRepository,
            tokenProvider);
    }

    @Test
    public void memberRegister() {
        //given
        MemberJoinDto memberJoinDto = new MemberJoinDto("userAlias", "token",
            Arrays.asList(1L, 2L));

        when(oAuthTokenRepository.findByIdWithLock(memberJoinDto.getToken())).thenReturn(
            Optional.of(OAuthToken.builder()
                .oAuthType(OAuthType.GOOGLE)
                .authUserId("authUserId")
                .build()));
        when(oAuthRepository.save(any())).thenReturn(null);
        doNothing().when(oAuthTokenRepository).delete(any());
        when(tokenProvider.getTokenByUserInfo(any())).thenReturn("JWT_TOKEN");
        when(skillRepository.findAllById(memberJoinDto.getSkillIds()))
            .thenReturn(
                Arrays.asList(
                    new Skill[]{
                        Skill.builder().skillName("a").build(),
                        Skill.builder().skillName("b").build()
                    }
                ));
        //when
        ResponseEntity responseEntity = memberService.join(memberJoinDto);

        //then
        assertAll(
            () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK),
            () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK),
            () -> assertThat(responseEntity.getBody()).isEqualTo("JWT_TOKEN")
        );

    }
}