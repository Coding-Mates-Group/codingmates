package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectRequestDto {
    private Long id;
    private String title;
    private String content;
//    private Blob contentBig;
    private Long views;
    private String recruitmentStatus;

    public Project toEntity(){
        return Project.builder()
                .title(title)
                .content(content)
                .views(views)
                .build();
    }
    @Builder
    public ProjectRequestDto(Long id, String title, String content, Long views, String recruitmentStatus){
        this.id = id;
        this.title = title;
        this.content = content;
        this.views = views;
        this.recruitmentStatus = recruitmentStatus;
    }
}
