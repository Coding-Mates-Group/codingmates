package com.gbc.codingmates.dto.oAuth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GithubUserInfoDTO {

    private String email;

    public void checkEmailExist() {
        if (ObjectUtils.isEmpty(email)) {
            throw new IllegalArgumentException();
        }
    }
}
