package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.domain.comment.Comment;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "comment")
    private List<Project> projects = new ArrayList<>();

    private String title;

    private String category;

    private LocalDateTime created_time, updated_time;


}
