package com.gbc.codingmates.dto;

import com.gbc.codingmates.domain.member.Member;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberDTO {

    private Long memberId;
    @NotNull
    private String username;

    private String gitRepository;

    public static MemberDTO from(Member member) {
        return MemberDTO.builder()
            .memberId(member.getId())
            .username(member.getUsername())
            .gitRepository(member.getResume().getGitRepository())
            .build();
    }
}
