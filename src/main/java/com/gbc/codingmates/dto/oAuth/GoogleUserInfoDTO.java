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
    private String id;

    public void checkIdExist() {
        if (ObjectUtils.isEmpty(id)) {
            throw new IllegalArgumentException();
        }
    }
}
