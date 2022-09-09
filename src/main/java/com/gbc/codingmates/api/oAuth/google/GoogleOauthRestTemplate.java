package com.gbc.codingmates.api.oAuth.google;


import com.gbc.codingmates.dto.oAuth.GoogleUserInfoDTO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleOauthRestTemplate {

    @Value("${spring.security.oauth2.client.registration.google.auth-code-url}")
    private String googleAuthCodeURL;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;
    @Value("${spring.security.oauth2.client.registration.google.redirect-url}")
    private String googleRedirectURL;

    @Value("${spring.security.oauth2.client.registration.google.userinfo-url}")
    private String googleUserInfoURL;

    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", googleClientId);
        params.put("client_secret", googleClientSecret);
        params.put("redirect_uri", googleRedirectURL);
        params.put("code", code);

        ResponseEntity<Map> response = restTemplate.postForEntity(googleAuthCodeURL,
            params, Map.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException();
        }

        return (String) response.getBody().get("access_token");
    }

    public GoogleUserInfoDTO getGoogleUserInfoByAccessToken(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);

        ResponseEntity<GoogleUserInfoDTO> response = restTemplate.exchange(googleUserInfoURL,
            HttpMethod.GET,
            new HttpEntity<>("", httpHeaders),
            GoogleUserInfoDTO.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException();
        }

        GoogleUserInfoDTO googleUserInfoDTO = response.getBody();
        googleUserInfoDTO.checkEmailExist();

        return googleUserInfoDTO;
    }

}
