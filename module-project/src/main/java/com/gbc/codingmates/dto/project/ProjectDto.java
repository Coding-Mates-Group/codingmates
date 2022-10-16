package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectDto {

    private Long id;
    private String title;
    private String content;
    private Long views;
    private String recruitmentStatus;
    private String username;

    public Project toEntity() {
        return Project.builder()
                .title(title)
                .content(content)
                .views(views)
                .build();
    }

    @Builder
    public ProjectDto(Project project){
        id = project.getId();
        title = project.getTitle();
        content = project.getContent();
        views = project.getViews();
        recruitmentStatus = project.getRecruitmentStatus();
    }
}
