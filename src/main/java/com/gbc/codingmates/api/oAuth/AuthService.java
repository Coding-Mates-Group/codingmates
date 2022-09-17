package com.gbc.codingmates.api.oAuth;

import com.gbc.codingmates.domain.member.OAuth;
import com.gbc.codingmates.domain.member.OAuthRepository;
import com.gbc.codingmates.domain.member.OAuthType;
import com.gbc.codingmates.dto.MemberDto;
import com.gbc.codingmates.dto.oAuth.GithubUserInfoDTO;
import com.gbc.codingmates.dto.oAuth.GoogleUserInfoDTO;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final GoogleOauthRestTemplate googleOauthRestTemplate;
    private final GithubOauthRestTemplate githubOauthRestTemplate;
    private final OAuthRepository oAuthRepository;

    public String getGoogleLoginURI() {
        return googleOauthRestTemplate.getAuthEndpointURI();
    }

    public MemberDto getMemberInfoByGoogleCode(String token) {
        String accessToken = googleOauthRestTemplate.getAccessToken(token);
        GoogleUserInfoDTO googleUserInfo = googleOauthRestTemplate.getGoogleUserInfoByAccessToken(
            accessToken);

        Optional<OAuth> oAuth = oAuthRepository.findByOAuthIdAndProvider(
            googleUserInfo.getId(), OAuthType.GOOGLE);

        if (oAuth.isPresent()) {
            return MemberDto.from(oAuth.get().getMember());
        }

        return null;
    }

    public String getGithubLoginURI() {
        return githubOauthRestTemplate.getAuthEndpointURI();
    }

    public MemberDto getMemberInfoByGithubCode(String code) {
        String accessToken = githubOauthRestTemplate.getAccessToken(code);
        GithubUserInfoDTO githubUserInfo = githubOauthRestTemplate.getUserInfoByAccessToken(
            accessToken);

        Optional<OAuth> oAuth = oAuthRepository.findByOAuthIdAndProvider(
            githubUserInfo.getId(), OAuthType.GITHUB);

        if (oAuth.isPresent()) {
            return MemberDto.from(oAuth.get().getMember());
        }

        return null;
    }
}
