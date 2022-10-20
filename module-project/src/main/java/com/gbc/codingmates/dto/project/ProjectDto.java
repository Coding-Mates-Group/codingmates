package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Schema(description = "DTO for posting projects")
public class ProjectDto {

    private Long id;

    @NotEmpty(message = "project title should not be empty!")
    @Length(min=5, max=30, message="project title should be 5 ~30 words long")
    @Pattern(regexp = "^[a-z0-9]*", message = "project tile should not contain any special characters")
    @Schema(description = "project title for posting", required = true, pattern = "^[a-z0-9]*", minLength = 5, maxLength = 30)
    private String title;

    @NotEmpty(message = "content of your project should not be empty!")
    @Schema(description = "project content for posting", required = true)
    private String content;

    private Long views;

    private String recruitmentStatus;

    private String userAlias;

//    public Project toEntity() {
//        return Project.builder()
//                .title(title)
//                .content(content)
//                .views(views)
//                .build();
//    }
//
//    @Builder
//    public ProjectDto(Project project){
//        id = project.getId();
//        title = project.getTitle();
//        content = project.getContent();
//        views = project.getViews();
//        recruitmentStatus = project.getRecruitmentStatus();
////        project.getMember().getuserAlias
//
//    }
}
