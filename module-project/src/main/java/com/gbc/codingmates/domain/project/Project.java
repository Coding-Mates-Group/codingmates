package com.gbc.codingmates.domain.project;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

//import module-member.
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gbc.codingmates.domain.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.dto.project.ProjectDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @JoinColumn(name = "member_id")
    private Long member_id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    private Long views;

    private LocalDateTime startDate, endDate;

    @Email(message = "Please enter a valid email")
    private String email;

    @Column(nullable = true)
    private String url;

    @Builder
    public Project(Long id, Long member_id, String title, String content, Long views, LocalDateTime startDate,
                   LocalDateTime endDate, String email, String url) {
        this.id = id;
        this.member_id = member_id;
        this.title = title;
        this.content = content;
        this.views = views;
        this.startDate = startDate;
        this.endDate = endDate;
        this.email = email;
        this.url = url;
    }

    public static Project toEntity(ProjectDto projectDto){
        return Project.builder()
                .title(projectDto.getTitle())
                .content(projectDto.getContent())
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .email(projectDto.getEmail())
                .url(projectDto.getUrl())
                .build();
    }

    public static ProjectDto from(Project project){
        return ProjectDto.builder()
                .title(project.getTitle())
                .content(project.getContent())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .email(project.getEmail())
                .url(project.getUrl())
                .build();
    }

    public void update(String title, String content, String email, String url) {
        this.title = title;
        this.content = content;
        this.email = email;
        this.url = url;
    }

    public void updateView(Long views) {
        this.views = views;
    }
}
