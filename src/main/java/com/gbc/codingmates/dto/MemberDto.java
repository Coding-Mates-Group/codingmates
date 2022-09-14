package com.gbc.codingmates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbc.codingmates.domain.member.Member;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MemberDto {

    private Long memberId;
    @NotNull
    private String username;

    private String gitRepository;
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public static MemberDto from(Member member) {
        return MemberDto.builder()
            .memberId(member.getId())
            .username(member.getUsername())
            .gitRepository(member.getResume().getGitRepository())
            .build();
    }


}
