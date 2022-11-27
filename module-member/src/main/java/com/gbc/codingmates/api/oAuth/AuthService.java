package com.gbc.codingmates.api.oAuth;

import com.gbc.codingmates.domain.member.RedisJwtToken;
import com.gbc.codingmates.domain.member.RedisJwtTokenRepository;
import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.OAuth;
import com.gbc.codingmates.domain.member.OAuthRepository;
import com.gbc.codingmates.domain.member.OAuthToken;
import com.gbc.codingmates.domain.member.OAuthTokenRepository;
import com.gbc.codingmates.domain.member.OAuthType;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.oAuth.AuthInfoDTO;
import com.gbc.codingmates.dto.response.AuthTokenResponseDTO;
import com.gbc.codingmates.jwt.TokenProvider;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private static final String MEMBER_JOIN_URI = "/members";
    private final OAuthTokenRepository oAuthTokenRepository;
    private final GoogleOauthRestTemplate googleOauthRestTemplate;
    private final GithubOauthRestTemplate githubOauthRestTemplate;
    private final FacebookOauthRestTemplate facebookOauthRestTemplate;
    private final OAuthRepository oAuthRepository;
    private final TokenProvider tokenProvider;
    private final RedisJwtTokenRepository redisJwtTokenRepository;
    private Map<OAuthType, OAuthRestTemplate> oauthTemplates;

    @PostConstruct
    public void init() {
        oauthTemplates = new HashMap<>();
        oauthTemplates.put(OAuthType.GOOGLE, googleOauthRestTemplate);
        oauthTemplates.put(OAuthType.FACEBOOK, facebookOauthRestTemplate);
        oauthTemplates.put(OAuthType.GITHUB, githubOauthRestTemplate);
    }


    public ResponseEntity getLoginURI(OAuthType oAuthType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(getOAuthTemplateByType(oAuthType).getAuthEndpointURI()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @Transactional
    public ResponseEntity authorizationByAuthCode(String code, OAuthType oAuthType) {
        OAuthRestTemplate oAuthRestTemplate = getOAuthTemplateByType(oAuthType);
        String accessToken = oAuthRestTemplate.getAccessToken(code);
        AuthInfoDTO authInfoDTO = oAuthRestTemplate.getUserInfoByAccessToken(accessToken);

        Optional<OAuth> oAuth = oAuthRepository.findByAuthIdAndProvider(
            authInfoDTO.getAuthUserId(), oAuthType);

        if (oAuth.isPresent()) {
            MemberDto memberDto = Member.getMemberDto(oAuth.get().getMember());

            String token = tokenProvider.getTokenByUserInfo(memberDto);
            redisJwtTokenRepository.save(new RedisJwtToken(token, memberDto, 1000L));

            return ResponseEntity.ok(token);
        } else {
            return getMemberJoinPath(authInfoDTO, oAuthType);
        }
    }


    private ResponseEntity getMemberJoinPath(AuthInfoDTO authInfoDTO, OAuthType oAuthType) {
        HttpHeaders headers = new HttpHeaders();

        OAuthToken oAuthToken = oAuthTokenRepository.save(OAuthToken.builder()
            .authUserId(authInfoDTO.getAuthUserId())
            .oAuthType(oAuthType)
            .email(authInfoDTO.getEmail())
            .build());

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .path(MEMBER_JOIN_URI)
            .queryParam("token", oAuthToken.getId())
            .build(true);

        headers.setLocation(URI.create(uriComponents.toString()));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    private OAuthRestTemplate getOAuthTemplateByType(OAuthType oAuthType) {
        OAuthRestTemplate oAuthRestTemplate = oauthTemplates.get(oAuthType);
        if (ObjectUtils.isEmpty(oAuthRestTemplate)) {
            throw new IllegalArgumentException("Cannot verify oauth Type");
        }
        return oAuthRestTemplate;
    }

    public AuthTokenResponseDTO getAuthTokenDetail(String token){
        OAuthToken oAuthToken = oAuthTokenRepository.findById(token)
            .orElseThrow(
                () -> new IllegalArgumentException("invalid auth token")
            );
        return AuthTokenResponseDTO.from(oAuthToken);
    }

}
