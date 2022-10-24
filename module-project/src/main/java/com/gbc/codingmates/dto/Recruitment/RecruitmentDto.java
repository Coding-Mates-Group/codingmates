package com.gbc.codingmates.dto.Recruitment;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Schema(description = "DTO for project recruitment")
public class RecruitmentDto {

    private Long id;

    private Long project_id;

    private String recruitmentType;

    private String recruitmentCount;

    private String recruitmentStatus;

    @Builder
    public RecruitmentDto(Recruitment recruitment){
        id = recruitment.getId();
        project_id = recruitment.getProject().getId();
        recruitmentType = recruitment.getRecruitmentType();
        recruitmentCount = recruitment.getRecruitmentCount();
        recruitmentStatus = recruitment.getRecruitmentStatus();
    }
}
