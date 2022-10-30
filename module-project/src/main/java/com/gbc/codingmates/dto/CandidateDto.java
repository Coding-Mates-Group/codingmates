package com.gbc.codingmates.dto;

import com.gbc.codingmates.domain.project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {
    private Long id;

    @NotEmpty
    private Long member_id;

    @NotEmpty
    private Project project_can;

    @NotEmpty
    private String result;

}
