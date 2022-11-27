package com.gbc.codingmates.service.oAuth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.gbc.codingmates.api.oAuth.AuthService;
import com.gbc.codingmates.api.oAuth.FacebookOauthRestTemplate;
import com.gbc.codingmates.api.oAuth.GithubOauthRestTemplate;
import com.gbc.codingmates.api.oAuth.GoogleOauthRestTemplate;
import com.gbc.codingmates.domain.member.OAuthRepository;
import com.gbc.codingmates.domain.member.OAuthToken;
import com.gbc.codingmates.domain.member.OAuthTokenRepository;
import com.gbc.codingmates.domain.member.OAuthType;
import com.gbc.codingmates.domain.member.RedisJwtTokenRepository;
import com.gbc.codingmates.dto.oAuth.GoogleAuthInfoDTO;
import com.gbc.codingmates.jwt.TokenProvider;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private AuthService authService;

    @Mock
    private OAuthTokenRepository oAuthTokenRepository;
    @Mock
    private GoogleOauthRestTemplate googleOauthRestTemplate;
    @Mock
    private GithubOauthRestTemplate githubOauthRestTemplate;
    @Mock
    private FacebookOauthRestTemplate facebookOauthRestTemplate;
    @Mock
    private OAuthRepository oAuthRepository;
    @Mock
    private TokenProvider tokenProvider;
    @Mock
    private GoogleAuthInfoDTO googleAuthInfoDTO;

    @Mock
    private RedisJwtTokenRepository redisJwtTokenRepository;
    @BeforeEach
    public void init() {
        authService = new AuthService(oAuthTokenRepository, googleOauthRestTemplate,
            githubOauthRestTemplate, facebookOauthRestTemplate, oAuthRepository, tokenProvider, redisJwtTokenRepository);
        authService.init();
    }

    @Test
    public void getMemberJointURI() {
        //given
        String authCode = "abcd";
        OAuthToken oAuthToken = OAuthToken.builder()
            .authUserId("authUserId")
            .oAuthType(OAuthType.GOOGLE)
            .build();
        OAuthType oAuthType = OAuthType.GOOGLE;

        when(googleOauthRestTemplate.getAccessToken(authCode)).thenReturn("accessToken");
        when(googleOauthRestTemplate.getUserInfoByAccessToken("accessToken")).thenReturn(
            googleAuthInfoDTO);
        when(googleAuthInfoDTO.getAuthUserId()).thenReturn("authUserId");
        when(oAuthRepository.findByAuthIdAndProvider("authUserId", oAuthType)).thenReturn(
            Optional.empty());
        when(oAuthTokenRepository.save(any())).thenReturn(oAuthToken);

        //when
        ResponseEntity responseEntity = authService.authorizationByAuthCode("abcd",
            oAuthType);

        //then
        assertAll(
            () -> assertThat(responseEntity.getStatusCode()).isEqualTo(
                HttpStatus.MOVED_PERMANENTLY),
            () -> assertThat(responseEntity.getHeaders().getLocation().getRawPath()).isEqualTo(
                "/members")
        );

    }
}