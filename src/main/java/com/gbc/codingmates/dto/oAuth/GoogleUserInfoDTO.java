package com.gbc.codingmates.dto.oAuth;

import static lombok.AccessLevel.PROTECTED;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class GoogleUserInfoDTO {

    private String email;
    private String name;
    private String given_name;
    private String family_name;
    private String locale;

    public void checkEmailExist() {
        if (ObjectUtils.isEmpty(email)) {
            throw new IllegalArgumentException();
        }
    }
}
