package com.gbc.codingmates.dto.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.dto.RecruitmentDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProjectCreateDto {

    //    private Long id;
    private Long member_id;
    private String title;
    private String content;
    private String createdDate;
    private String email;
    private List<Recruitment> recruitmentList;

    private String recruitmentType;

    private int recruitmentCount;

    private String recruitmentStatus;

    @Builder
    public ProjectCreateDto(Project project){
        this.member_id = project.getMember_id();
        this.title= project.getTitle();
        this.content= project.getContent();
        this.createdDate = String.valueOf(project.getCreatedDate());
        this.email = project.getEmail();
        this.recruitmentList = project.getRecruitmentList();
//        this.recruitmentType = project.getRecruitmentList();
//        this.recruitmentType = recruitment.getRecruitmentType();
//        this.recruitmentCount = project.getRecruitmentList()
//        this.recruitmentStatus = recruitment.getRecruitmentStatus();
//        this.recruitmentList = recruitment
//        this.recruitmentDtoList = project.getRecruitmentList().stream().map(RecruitmentDto::new).collect(Collectors.toList());
    }
//    email, id, member_id, recruitmentStatus, views는 서버에서 처리하면 될 것 같은데 어떠신가요??

    public Project toEntity(){
        return Project.builder()
                .member_id(member_id)
                .title(title)
                .content(content)
                .email(email)
//                .recruitmentList()
                .build();
    }

}
