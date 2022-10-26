package com.gbc.codingmates.dto.Recruitment;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.dto.bookmark.BookmarkDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Schema(description = "DTO for project recruitment")
public class RecruitmentDto {

    private Long id;

    private Project project_recr;

    private String recruitmentType;

    private int recruitmentCount;

    private String recruitmentStatus;


}
