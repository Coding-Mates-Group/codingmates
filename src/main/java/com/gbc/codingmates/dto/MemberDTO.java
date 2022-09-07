package com.gbc.codingmates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbc.codingmates.domain.member.Member;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberDTO {

    @NotNull
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public static MemberDTO from(Member member) {
        return MemberDTO.builder()
            .username(member.getEmail())
            .password(member.getPassword())
            .build();
    }
}
