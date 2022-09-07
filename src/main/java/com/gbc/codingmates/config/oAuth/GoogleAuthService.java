package com.gbc.codingmates.config.oAuth;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.MemberRepository;
import com.gbc.codingmates.dto.MemberDTO;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {
    private final GoogleOauthRestTemplate googleOauthRestTemplate;
    private final MemberRepository memberRepository;

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
        queryParams.add("scope", "https://www.googleapis.com/auth/userinfo.email");
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
        String email = googleOauthRestTemplate.getEmailByAccessToken(accessToken);
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
            return MemberDTO.from(member.get());
        }
        return null;
    }
}
