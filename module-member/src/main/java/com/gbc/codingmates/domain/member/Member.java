package com.gbc.codingmates.domain.member;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static org.springframework.util.ObjectUtils.isEmpty;

import com.gbc.codingmates.domain.BaseTimeEntity;
import com.gbc.codingmates.dto.form.MemberJoinDto;
import com.gbc.codingmates.dto.member.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gbc.codingmates.utils.FileHandler;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    @JsonIgnore
    private Long id;

    @NotBlank(message = "Your id:")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$", message = "3~12 in length, with no special characters")
    @Column(length = 15, nullable = false, unique = true)
    @JsonIgnore
    private String username;

    @Column(length = 30)
    private String email;

    @Column(length = 62)
    @JsonIgnore
    private String password;

    @Enumerated(STRING)
    private MemberStatus memberStatus;

    @Embedded
    private Resume resume = new Resume();
    private String memberProfilePath;

    public static Member from(MemberJoinDto memberJoinDto) {
        if (isEmpty(memberJoinDto.getUserAlias())) {
            throw new IllegalArgumentException("user alias is inquired for create Member");
        }
        return Member.builder()
            .username(memberJoinDto.getUserAlias())
            .memberStatus(MemberStatus.BASIC)
            .memberProfilePath(FileHandler.getRandomProfilePath())
            .build();
    }

    public static MemberDto getMemberDto(Member member) {
        return MemberDto.builder()
            .username(member.getUsername())
            .memberStatus(member.getMemberStatus().name())
            .password(member.getPassword())
            .gitRepository(member.getResume() != null ? member.getResume().getGitRepository() : "")
            .build();
    }

    @Builder
    public Member(String username, String email, String password, MemberStatus memberStatus,
        Resume resume, String memberProfilePath) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.memberStatus = memberStatus;
        this.resume = resume;
        this.memberProfilePath = memberProfilePath;
    }

    public void mapMemberProfileImagePath(String imagePath) {
        this.memberProfilePath = imagePath;
    }
}
