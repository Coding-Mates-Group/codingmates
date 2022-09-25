package com.gbc.codingmates.api.oAuth;

import com.gbc.codingmates.dto.oAuth.AuthInfoDTO;
import org.springframework.web.client.RestTemplate;

public abstract class OAuthRestTemplate {

    public abstract String getAuthEndpointURI();

    public abstract String getAccessToken(String code);

    public abstract AuthInfoDTO getUserInfoByAccessToken(String accessToken);

    protected RestTemplate settingRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new OAuthErrorHandler());
        return restTemplate;
    }
}
