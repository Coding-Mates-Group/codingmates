package com.gbc.codingmates.dto.oAuth;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FacebookAuthInfoDTO extends AuthInfoDTO {

    private Map<String, Object> data = new HashMap<>();
    private String accessToken;

    @Override
    void checkIdIsNull() {
        if (ObjectUtils.isEmpty(data.get("user_id")) || !(Boolean)data.get("is_valid")) {
            throw new IllegalArgumentException("Facebook OAuth User Info don't have id value");
        }
    }

    @Override
    void checkAccessTokenIsNull() {
        if (ObjectUtils.isEmpty(accessToken) || !(Boolean)data.get("is_valid")) {
            throw new IllegalArgumentException(
                "Facebook OAuth User Info don't have accessToken value");
        }
    }

    @Override
    public String getAuthUserId() {
        checkIdIsNull();
        return (String) data.get("user_id");
    }

    @Override
    public String getAccessToken() {
        checkAccessTokenIsNull();
        return accessToken;
    }

    public void saveAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
}
