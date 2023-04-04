package com.gbc.codingmates.dto.project.request;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.dto.RecruitmentDto;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProjectCreateRequestDto {

    private Long id;
    private Long member_id;
    private String title;
    private String content;
    private String createdDate;
    private String email;
    private List<RecruitmentDto> recruitmentDtoList;

    private String recruitmentType;

    private int recruitmentCount;

    private String recruitmentStatus;

    //entity to dto
    public ProjectCreateRequestDto(Project project, Recruitment recruitment){
        this.id= project.getId();
        this.member_id = project.getMember_id();
        this.title= project.getTitle();
        this.content= project.getContent();
        this.createdDate = String.valueOf(project.getCreatedDate());
        this.email = project.getEmail();
        this.recruitmentType = recruitment.getRecruitmentType();
        this.recruitmentCount = recruitment.getRecruitmentCount();
        this.recruitmentStatus = recruitment.getRecruitmentStatus();
//        this.recruitmentDtoList = project.getRecruitmentList().stream().map(RecruitmentDto::new).collect(Collectors.toList());
    }
//    email, id, member_id, recruitmentStatus, views는 서버에서 처리하면 될 것 같은데 어떠신가요??
}
