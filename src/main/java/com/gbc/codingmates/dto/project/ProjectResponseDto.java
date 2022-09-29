package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProjectResponseDto {
    private Long id;
    private String title;
    private String content;

    public ProjectResponseDto(Project project){
        this.id= project.getId();
        this.title= project.getTitle();
        this.content= project.getContent();

    }
}
