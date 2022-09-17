package com.gbc.codingmates.config;

import java.util.List;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


@Getter
public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    private Long memberId;

    public UserAuthentication(String principal, String credentials,
        List<GrantedAuthority> authorities, Long memberId) {
        super(principal, credentials, authorities);
        this.memberId = memberId;
    }
}
