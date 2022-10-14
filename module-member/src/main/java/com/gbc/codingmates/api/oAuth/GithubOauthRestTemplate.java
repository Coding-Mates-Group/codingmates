package com.gbc.codingmates.api.oAuth;

import com.gbc.codingmates.dto.oAuth.AuthInfoDTO;
import com.gbc.codingmates.dto.oAuth.GithubAuthInfoDTO;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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
public class GithubOauthRestTemplate extends OAuthRestTemplate {

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
        queryParams.add("scope", "user:email");

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
        params.put("client_id", clientId);
        params.put("client_secret", secret);
        params.put("code", code);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenEndpoint,
            params, Map.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException(response.getBody().toString());
        }

        return (String) response.getBody().get("access_token");
    }

    public AuthInfoDTO getUserInfoByAccessToken(String accessToken) {
        RestTemplate restTemplate = settingRestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        httpHeaders.add("X-OAuth-Scopes", "user:email");
        httpHeaders.add("X-Accepted-OAuth-Scopes", "user:email");

        ResponseEntity<GithubAuthInfoDTO> response = restTemplate.exchange(fetchingDataEndpoint,
            HttpMethod.GET,
            new HttpEntity<>("", httpHeaders),
            GithubAuthInfoDTO.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException();
        }

        GithubAuthInfoDTO userInfoDTO = response.getBody();

        if(userInfoDTO.getEmail() == null){
            String privateEmail = getPrivateEmail(accessToken);
            userInfoDTO.saveEmail(privateEmail);
        }

        userInfoDTO.saveAccessToken(accessToken);

        return userInfoDTO;
    }

    private String getPrivateEmail(String accessToken) {
        RestTemplate restTemplate = settingRestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        httpHeaders.add("Accept", "application/vnd.github+json");

        ResponseEntity<List> response = restTemplate.exchange("https://api.github.com/user/emails",
            HttpMethod.GET,
            new HttpEntity<>("", httpHeaders),
            List.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException("Github user can not provide user's email");
        }

        Optional primaryEmail = response.getBody().stream()
            .filter(o -> ((Map) o).get("primary").equals(true))
            .findFirst();

        if(primaryEmail.isPresent()){
            return (String) ((Map) primaryEmail.get()).get("email");
        }

        throw new IllegalArgumentException("Github user can not provide user's email");
    }
}
