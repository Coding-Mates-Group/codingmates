package com.gbc.codingmates.api.oAuth;

import com.gbc.codingmates.domain.member.OAuth;
import com.gbc.codingmates.domain.member.OAuthRepository;
import com.gbc.codingmates.domain.member.OAuthType;
import com.gbc.codingmates.dto.MemberDto;
import com.gbc.codingmates.dto.oAuth.AuthInfoDTO;
import com.gbc.codingmates.jwt.TokenProvider;
import java.net.URI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private static final String MEMBER_JOIN_URI = "/login/join";

    private final GoogleOauthRestTemplate googleOauthRestTemplate;
    private final GithubOauthRestTemplate githubOauthRestTemplate;
    private final OAuthRepository oAuthRepository;
    private final TokenProvider tokenProvider;

    public ResponseEntity getLoginURI(OAuthType oAuthType) {
        String uri = "";
        if (oAuthType == OAuthType.GOOGLE) {
            uri = googleOauthRestTemplate.getAuthEndpointURI();
        } else if (oAuthType == OAuthType.GITHUB) {
            uri = githubOauthRestTemplate.getAuthEndpointURI();
        } else if (oAuthType == OAuthType.FACEBOOK) {
            // TODO: 2022/09/17 set facebook oAuth Endpoint
            uri = "FACEBOOK AUTH ENDPOINT";
        } else {
            throw new IllegalArgumentException("Cannot verify oauth Type");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(uri));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }


    public ResponseEntity authorizationByAuthCode(String code, OAuthType oAuthType) {
        AuthInfoDTO authInfoDTO = null;
        if (oAuthType == OAuthType.GOOGLE) {
            authInfoDTO = getMemberInfoByGoogleCode(code);
        } else if (oAuthType == OAuthType.GITHUB) {
            authInfoDTO = getMemberInfoByGithubCode(code);
        } else if (oAuthType == OAuthType.FACEBOOK) {
            authInfoDTO = getMemberInfoByFacebookCode(code);
        } else {
            throw new IllegalArgumentException("Cannot verify oauth Type");
        }

        Optional<OAuth> oAuth = oAuthRepository.findByAuthIdAndProvider(
            authInfoDTO.getAuthUserId(), oAuthType);
        if (oAuth.isPresent()) {
            return ResponseEntity.ok(tokenProvider.getTokenByUserInfo(
                MemberDto.from(oAuth.get().getMember())
            ));
        } else {
            return getMemberJoinPath(authInfoDTO.getAuthUserId(), authInfoDTO.getAccessToken(),
                oAuthType);
        }
    }

    private AuthInfoDTO getMemberInfoByGoogleCode(String code) {
        String accessToken = googleOauthRestTemplate.getAccessToken(code);
        return googleOauthRestTemplate.getGoogleUserInfoByAccessToken(
            accessToken);
    }

    private AuthInfoDTO getMemberInfoByFacebookCode(String code) {
        // TODO: 2022/09/17 facebookInfo
        return null;
    }

    private AuthInfoDTO getMemberInfoByGithubCode(String code) {
        String accessToken = githubOauthRestTemplate.getAccessToken(code);
        return githubOauthRestTemplate.getUserInfoByAccessToken(
            accessToken);
    }

    private ResponseEntity getMemberJoinPath(String authId, String accessToken,
        OAuthType oAuthType) {
        HttpHeaders headers = new HttpHeaders();
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .path(MEMBER_JOIN_URI)
            .queryParam("userId", authId)
            .queryParam("token", accessToken)
            .queryParam("oauth", oAuthType.name())
            .build(true);

        headers.setLocation(URI.create(uriComponents.toString()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
