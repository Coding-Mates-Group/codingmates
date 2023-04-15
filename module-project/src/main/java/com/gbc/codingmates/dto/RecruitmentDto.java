package com.gbc.codingmates.dto;

import com.gbc.codingmates.domain.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Schema(description = "DTO for posting project recruitment details")
public class RecruitmentDto {

    @NotEmpty
    private String recruitmentType;

    @NotNull
    private int recruitmentCount;

    @NotEmpty
    private String recruitmentStatus;
}
