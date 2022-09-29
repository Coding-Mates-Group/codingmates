package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectRequestDto {
    private String title;
    private String content;
    private Blob contentBig;
    private Long views;

    public Project toEntity(){
        return Project.builder()
                .title(title)
                .content(content)
                .views(views)
                .build();
    }
}
