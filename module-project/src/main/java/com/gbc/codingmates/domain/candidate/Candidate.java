package com.gbc.codingmates.domain.candidate;

import com.gbc.codingmates.domain.BaseTimeEntity;
import com.gbc.codingmates.domain.project.Project;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.dto.CandidateDto;
import com.gbc.codingmates.dto.RecruitmentDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Candidate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long id;

    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_Comment_Member"))
    private Long member_id;

    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_candidate_project_can"))
    private Project project_can;

    @Column(nullable = false)
    private String result;

    public static Candidate toEntity(CandidateDto candidateDto){
        return Candidate.builder()
                .id(candidateDto.getId())
                .member_id(candidateDto.getMember_id())
                .project_can(candidateDto.getProject_can())
                .result(candidateDto.getResult())
                .build();
    }

    public static CandidateDto from(Candidate candidate){
        return CandidateDto.builder()
                .id(candidate.getId())
                .member_id(candidate.getMember_id())
                .project_can(candidate.getProject_can())
                .result(candidate.getResult())
                .build();
    }

    @Builder
    public Candidate(Long id, Long member_id, Project project_can, String result){
        this.id = id;
        this.member_id = member_id;
        this.project_can = project_can;
        this.result = result;
    }
}
