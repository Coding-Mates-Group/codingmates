package com.gbc.codingmates.domain.project;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

//import module-member.
import com.gbc.codingmates.domain.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.dto.ProjectDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@SequenceGenerator(
        name = "PROJECT_SEQ_GENERATOR",
        sequenceName = "PROJECT_SEQ",
        initialValue = 1, allocationSize = 50)
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    private LocalDateTime modifyToot;

    private String recruitmentStatus;

    private String result;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    Set<Bookmark> bookmarks = new HashSet<>();

    @Email(message = "Please enter a valid email")
    private String email;

    @Column(nullable = true)
    private String url;

    public static Project toEntity(ProjectDto projectDto){
        return Project.builder()
                .id(projectDto.getId())
                .member_id(projectDto.getMember_id())
                .title(projectDto.getTitle())
                .content(projectDto.getContent())
                .views(projectDto.getViews())
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .recruitmentStatus(projectDto.getRecruitmentStatus())
                .email(projectDto.getEmail())
                .url(projectDto.getUrl())
                .build();
    }

    public static ProjectDto from(Project project){
        return ProjectDto.builder()
                .id(project.getId())
                .member_id(project.getMember_id())
                .title(project.getTitle())
                .content(project.getContent())
                .views(project.getViews())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .recruitmentStatus(project.getRecruitmentStatus())
                .email(project.getEmail())
                .url(project.getUrl())
                .build();
    }

    @Builder
    public Project(Long id, Long member_id, String title, String content, Long views, LocalDateTime startDate, LocalDateTime endDate,
                   String recruitmentStatus, String email, String url) {
        this.id = id;
        this.member_id = member_id;
        this.title = title;
        this.content = content;
        this.views = views;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitmentStatus = recruitmentStatus;
        this.email = email;
        this.url = url;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateView(Long views) {
        this.views = views;
    }
}
