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
    private Project project;

    private String recruitmentType;

    private String recruitmentCount;

    private String recruitmentStatus;
}
