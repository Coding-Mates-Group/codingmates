package com.gbc.codingmates.api.oAuth;

import com.gbc.codingmates.dto.oAuth.AuthInfoDTO;
import com.gbc.codingmates.dto.oAuth.FacebookAuthInfoDTO;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class FacebookOauthRestTemplate extends OAuthRestTemplate {

    private String authEndpointURI = "";
    @Value("${spring.security.oauth2.client.registration.facebook.auth-endpoint}")
    private String authEndpoint;

    @Value("${spring.security.oauth2.client.registration.facebook.redirect-url}")
    private String redirectURL;
    @Value("${spring.security.oauth2.client.registration.facebook.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.facebook.client-secret}")
    private String secret;

    @Value("${spring.security.oauth2.client.registration.facebook.token-endpoint}")
    private String tokenEndpoint;

    @Value("${spring.security.oauth2.client.registration.facebook.fetching_data_endpoint}")
    private String fetchingDataEndpoint;

    @PostConstruct
    public void init() {
        Map<String, List<String>> param = new HashMap<>();
        MultiValueMap<String, String> queryParams = new MultiValueMapAdapter<>(param);
        queryParams.add("client_id", clientId);
        queryParams.add("redirect_uri", redirectURL);
        queryParams.add("state", "{st=state123abc,ds=123456789}");

        URI uri = UriComponentsBuilder
            .fromUriString(authEndpoint)
            .queryParams(queryParams)
            .build()
            .toUri();
        authEndpointURI = uri.toString();
    }

    public String getAuthEndpointURI() {
        return authEndpointURI;
    }


    public String getAccessToken(String code) {
        RestTemplate restTemplate = settingRestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("redirect_uri", redirectURL);
        params.put("client_id", clientId);
        params.put("client_secret", secret);
        params.put("code", code);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenEndpoint, params,
            Map.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException();
        }

        return (String) response.getBody().get("access_token");
    }

    public AuthInfoDTO getUserInfoByAccessToken(String accessToken) {
        RestTemplate restTemplate = settingRestTemplate();

        Map<String, List<String>> param = new HashMap<>();
        MultiValueMap<String, String> queryParams = new MultiValueMapAdapter<>(param);
        queryParams.add("input_token", accessToken);
        queryParams.add("redirect_uri", redirectURL);
        queryParams.add("client_id", clientId);
        queryParams.add("client_secret", secret);
        queryParams.add("access_token", accessToken);

        URI uri = UriComponentsBuilder
            .fromUriString(fetchingDataEndpoint)
            .queryParams(queryParams)
            .build()
            .toUri();

        ResponseEntity<FacebookAuthInfoDTO> response = restTemplate.getForEntity(uri.toString(),
            FacebookAuthInfoDTO.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException();
        }

        response.getBody().saveAccessToken(accessToken);

        return response.getBody();
    }
}
