package com.gbc.codingmates.domain.member;

import com.gbc.codingmates.domain.comment.Comment;
import com.gbc.codingmates.domain.project.Project;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @NotBlank(message = "Your id:")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$", message = "3~12 in length, with no special characters")
    @Column(length=15, nullable = false)
    private String username;

    @Column(length =30, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private String password;

    private LocalDateTime created_time, updated_time;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "project")
    private List<Member> members1 = new ArrayList<>();

    @OneToMany(mappedBy = "comment")
    private List<Member> members2 = new ArrayList<>();

    @Builder
    public Member(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }



}
