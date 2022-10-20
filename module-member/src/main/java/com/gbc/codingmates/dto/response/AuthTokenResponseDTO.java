package com.gbc.codingmates.dto.response;

import com.gbc.codingmates.domain.member.OAuthToken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AuthTokenResponseDTO {
    private String email;
    private String oAuthType;
    private String oAuthUserId;

    public static AuthTokenResponseDTO from(OAuthToken oAuthToken){
        return AuthTokenResponseDTO.builder()
            .email(oAuthToken.getEmail())
            .oAuthType(oAuthToken.getOAuthType().name())
            .oAuthUserId(oAuthToken.getAuthUserId())
            .build();
    }

}
