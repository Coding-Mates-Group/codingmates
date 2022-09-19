package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.member.MemberStatus;
import com.gbc.codingmates.domain.project.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto1 {

    private String title;
    private String content;

    public Project toEntity1(){
        return Project.builder()
                .title(title)
                .content(content)
                .build();
    }

}
