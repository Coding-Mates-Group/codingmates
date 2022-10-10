package com.gbc.codingmates.dto.oAuth;

public abstract class AuthInfoDTO {

    abstract void checkIdIsNull();

    abstract void checkAccessTokenIsNull();

    public abstract String getAuthUserId();

    public abstract String getAccessToken();
}
