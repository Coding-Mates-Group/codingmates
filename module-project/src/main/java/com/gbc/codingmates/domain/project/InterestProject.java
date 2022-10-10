package com.gbc.codingmates.domain.project;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gbc.codingmates.domain.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class InterestProject extends BaseTimeEntity {

    @Id
    @Column(name = "INTEREST_PROJECT_ID")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_InterestProject_Project"))
    private Project project;

    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_InterestProject_Member"))
    private Long member_id;

    private Boolean acceptInfo;

    @Builder
    public InterestProject(Project project, Long memberId, Boolean acceptInfo) {
        this.project = project;
        this.member_id = memberId;
        this.acceptInfo = acceptInfo;
    }
}
