package com.gbc.codingmates.dto;

import com.gbc.codingmates.domain.skill.Skill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Schema(description = "DTO for posting required skills in project post")
@Builder
public class ProjectSkillDto {
    private Long projectSkillId;
    private String skillName;
//    private Skill skill;
}
