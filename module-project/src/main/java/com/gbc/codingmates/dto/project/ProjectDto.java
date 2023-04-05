package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.dto.BookmarkDto;
import com.gbc.codingmates.dto.RecruitmentDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Schema(description = "DTO for posting projects")
@Builder
public class ProjectDto {

    private Long id;

    private Long member_id;

    @NotEmpty(message = "project title should not be empty!")
    @Length(min=5, max=30, message="project title should be 5 ~30 words long")
    @Pattern(regexp = "^[a-z0-9]*", message = "project tile should not contain any special characters")
    @Schema(description = "project title for posting", required = true, pattern = "^[a-z0-9]*", minLength = 5, maxLength = 30)
    private String title;

    @NotEmpty(message = "content of your project should not be empty!")
    @Schema(description = "project content for posting", required = true)
    private String content;

    private Long views;

    private LocalDateTime startDate, endDate;

    private Set<Bookmark> bookmarkDtoSet;

    private List<Recruitment> recruitmentDtoList;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    private String url;

    @Schema(description = "skill stack for your project")
    private String[] skillStack;
}
