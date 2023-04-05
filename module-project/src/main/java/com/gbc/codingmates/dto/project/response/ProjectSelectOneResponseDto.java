package com.gbc.codingmates.dto.project.response;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.dto.RecruitmentDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProjectSelectOneResponseDto extends ProjectDto {

    private Long id;
    private String title;
    private String content;
    private String createdDate, modifiedDate;
    private Long member_id;
    private List<RecruitmentDto> recruitmentDtoList;

    //entity to dto
    public ProjectSelectOneResponseDto(Project project){
        this.id= project.getId();
        this.title= project.getTitle();
        this.content= project.getContent();
        this.createdDate = String.valueOf(project.getCreatedDate());
        this.modifiedDate = String.valueOf(project.getModifiedDate());
        this.member_id = project.getMember_id();
//        this.recruitmentDtoList = project.getRecruitmentList().stream().map(RecruitmentDto::new).collect(Collectors.toList());
//        this.comments = project.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
