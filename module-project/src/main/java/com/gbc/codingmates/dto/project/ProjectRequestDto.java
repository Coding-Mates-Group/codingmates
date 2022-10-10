package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import java.sql.Blob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectRequestDto {

    private String title;
    private String content;
    private Blob contentBig;

    public Project toEntity() {
        return Project.builder()
            .title(title)
            .content(content)
            .build();
    }
}
