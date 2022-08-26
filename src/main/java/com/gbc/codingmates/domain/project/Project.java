package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Project {
    @Id @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    @OneToMany(mappedBy = "project")
    List<Member> members = new ArrayList<>();

    private String title;

    private String category;

    private LocalDateTime created_time, updated_time;


}
