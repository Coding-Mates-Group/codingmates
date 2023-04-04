package com.gbc.codingmates.domain.recruitment;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gbc.codingmates.domain.BaseTimeEntity;
import com.gbc.codingmates.domain.project.Project;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gbc.codingmates.dto.RecruitmentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Recruitment extends BaseTimeEntity {

    @Id
    @Column(name = "recruitment_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_recruitment_project"))
    private Project project_recr;

    private String recruitmentType;

    private int recruitmentCount;

    private String recruitmentStatus;

    public static Recruitment toEntity(RecruitmentDto recruitmentDto){
        return Recruitment.builder()
                .id(recruitmentDto.getId())
                .project_recr(recruitmentDto.getProject_recr())
                .recruitmentCount(recruitmentDto.getRecruitmentCount())
                .recruitmentStatus(recruitmentDto.getRecruitmentStatus())
                .recruitmentType(recruitmentDto.getRecruitmentType())
                .build();
    }

    public static RecruitmentDto from(Recruitment recruitment){
        return RecruitmentDto.builder()
                .id(recruitment.getId())
                .project_recr(recruitment.getProject_recr())
                .recruitmentType(recruitment.getRecruitmentType())
                .recruitmentCount(recruitment.getRecruitmentCount())
                .recruitmentStatus(recruitment.getRecruitmentStatus())
                .build();
    }

    @Builder
    public Recruitment(Long id, Project project_recr, String recruitmentType,
                       int recruitmentCount, String recruitmentStatus){
        this.id = id;
        this.project_recr = project_recr;
        this.recruitmentCount = recruitmentCount;
        this.recruitmentType = recruitmentType;
        this.recruitmentStatus = recruitmentStatus;
    }

    public void setRecruitment(Project project_recr){
        if(this.project_recr!=null){
            this.project_recr.getRecruitmentList().remove(this);
        }
        this.project_recr = project_recr;
        project_recr.getRecruitmentList().add(this);
    }
}
