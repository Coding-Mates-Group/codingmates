package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.recruitment.Recruitment;
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
    private int recruitmentCount;
    private Recruitment recruitment;


    @Builder
    public ProjectListDto(Project project){
        id = project.getId();
        title = project.getTitle();
        views = project.getViews();
        int recruitmentCount = recruitment.getRecruitmentCount();
    }
}
