package com.gbc.codingmates.api.oAuth;

import com.gbc.codingmates.dto.oAuth.GithubAuthInfoDTO;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GithubOauthRestTemplate {

    private String authEndpointURI = "";
    @Value("${spring.security.oauth2.client.registration.github.auth-endpoint}")
    private String authEndpoint;

    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
    private String secret;

    @Value("${spring.security.oauth2.client.registration.github.token-endpoint}")
    private String tokenEndpoint;

    @Value("${spring.security.oauth2.client.registration.github.fetching_data_endpoint}")
    private String fetchingDataEndpoint;

    @PostConstruct
    public void init() {
        Map<String, List<String>> param = new HashMap<>();
        MultiValueMap<String, String> queryParams = new MultiValueMapAdapter<>(param);
        queryParams.add("client_id", clientId);

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
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", secret);
        params.put("code", code);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenEndpoint,
            params, Map.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException();
        }

        return (String) response.getBody().get("access_token");
    }

    public GithubAuthInfoDTO getUserInfoByAccessToken(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);

        ResponseEntity<GithubAuthInfoDTO> response = restTemplate.exchange(fetchingDataEndpoint,
            HttpMethod.GET,
            new HttpEntity<>("", httpHeaders),
            GithubAuthInfoDTO.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException();
        }

        GithubAuthInfoDTO userInfoDTO = response.getBody();
        userInfoDTO.saveAccessToken(accessToken);

        return userInfoDTO;
    }
}
