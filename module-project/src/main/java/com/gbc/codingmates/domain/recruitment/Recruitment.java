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

import java.util.List;
import java.util.stream.Collectors;

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

    @Builder
    public Recruitment(Long id, Project project_recr, String recruitmentType,
                       int recruitmentCount, String recruitmentStatus){
        this.id = id;
        this.project_recr = project_recr;
        this.recruitmentCount = recruitmentCount;
        this.recruitmentType = recruitmentType;
        this.recruitmentStatus = recruitmentStatus;
    }

    public static Recruitment toEntity(RecruitmentDto recruitmentDto){
        return Recruitment.builder()
                .recruitmentCount(recruitmentDto.getRecruitmentCount())
                .recruitmentStatus(recruitmentDto.getRecruitmentStatus())
                .recruitmentType(recruitmentDto.getRecruitmentType())
                .build();
    }

    public static List<Recruitment> toEntityList(List<RecruitmentDto> recruitmentDtoList){
        return recruitmentDtoList.stream()
                .map(recruitmentDto -> Recruitment.toEntity(recruitmentDto))
                .collect(Collectors.toList());
    }

    public static RecruitmentDto from(Recruitment recruitment){
        return RecruitmentDto.builder()
                .recruitmentType(recruitment.getRecruitmentType())
                .recruitmentCount(recruitment.getRecruitmentCount())
                .recruitmentStatus(recruitment.getRecruitmentStatus())
                .build();
    }

    public static List<RecruitmentDto> from(List<Recruitment> recruitmentList){
        return recruitmentList.stream()
                .map(recruitment -> Recruitment.from(recruitment))
                .collect(Collectors.toList());
    }

    public void setProject_recr(Project project_recr){
        this.project_recr = project_recr;
    }
}
