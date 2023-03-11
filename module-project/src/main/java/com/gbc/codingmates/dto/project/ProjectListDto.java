package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectListDto {
    private Long id;
    private String title;
    private Long views;

    @Builder
    public ProjectListDto(Project project){
        id = project.getId();
        title = project.getTitle();
        views = project.getViews();
    }
}
