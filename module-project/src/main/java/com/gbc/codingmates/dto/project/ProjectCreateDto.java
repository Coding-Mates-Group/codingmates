package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.dto.RecruitmentDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Schema(description = "DTO for posting projects")
@Builder
public class ProjectCreateDto {
    private ProjectDto projectDto;

    private List<RecruitmentDto> recruitmentDtoList;
}
