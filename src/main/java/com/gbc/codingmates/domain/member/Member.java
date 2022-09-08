package com.gbc.codingmates.domain.member;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gbc.codingmates.domain.BaseTimeEntity;

import javax.persistence.*;
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
    private Long id;

    @NotBlank(message = "Your id:")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$", message = "3~12 in length, with no special characters")
    @Column(length = 15, nullable = false)
    @JsonIgnore
    private String username;

    @Enumerated(STRING)
    private MemberStatus status;

    @Embedded
    private Resume resume = new Resume();

    @Builder
    public Member(String username, MemberStatus status, Resume resume) {
        this.username = username;
        this.status = status;
        this.resume = resume;
    }
}
