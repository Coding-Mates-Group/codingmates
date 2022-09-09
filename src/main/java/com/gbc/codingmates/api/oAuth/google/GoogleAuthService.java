package com.gbc.codingmates.api.oAuth.google;

import com.gbc.codingmates.domain.member.OAuth;
import com.gbc.codingmates.domain.member.OAuthRepository;
import com.gbc.codingmates.domain.member.OAuthType;
import com.gbc.codingmates.dto.MemberDTO;
import com.gbc.codingmates.dto.oAuth.GoogleUserInfoDTO;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoogleAuthService {

    private final GoogleOauthRestTemplate googleOauthRestTemplate;
    private final OAuthRepository oAuthRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-url}")
    private String googleRedirectURL;

    @Value("${spring.security.oauth2.client.registration.google.login-form}")
    private String loginURL;

    @PostConstruct
    public void init() {
        Map<String, List<String>> param = new HashMap<>();
        MultiValueMap<String, String> queryParams = new MultiValueMapAdapter<>(param);
        queryParams.add("client_id", googleClientId);
        queryParams.add("redirect_uri", googleRedirectURL);
        queryParams.add("response_type", "code");
        queryParams.add("scope",
            "https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile");
        queryParams.add("approval_prompt", "force");
        queryParams.add("access_type", "offline");

        URI uri = UriComponentsBuilder
            .fromUriString("https://accounts.google.com/o/oauth2/auth")
            .queryParams(queryParams)
            .build()
            .toUri();
        loginURL = uri.toString();
    }

    public String getGoogleLoginFormURI() {
        return loginURL;
    }


    public MemberDTO getMemberInfoByGoogleToken(String token) {
        String accessToken = googleOauthRestTemplate.getAccessToken(token);
        GoogleUserInfoDTO googleUserInfo = googleOauthRestTemplate.getGoogleUserInfoByAccessToken(
            accessToken);

        Optional<OAuth> oAuth = oAuthRepository.findByEmailAndProvider(
            googleUserInfo.getEmail(), OAuthType.GOOGLE);

        if (oAuth.isPresent()) {
            return MemberDTO.from(oAuth.get().getMember());
        }

        return null;
    }
}
