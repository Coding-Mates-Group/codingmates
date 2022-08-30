package com.gbc.codingmates.domain.skill;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gbc.codingmates.domain.project.Project;
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
public class ProjectSkill {

    @Id
    @Column(name = "PROJECT_SKILL_ID")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PROJECT_ID", foreignKey = @ForeignKey(name = "FK_PROJECTSKILL_PROJECT"))
    private Project project;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "SKILL_ID", foreignKey = @ForeignKey(name = "FK_PROJECTSKILL_SKILL"))
    private Skill skill;

    @Builder
    public ProjectSkill(Project project, Skill skill) {
        this.project = project;
        this.skill = skill;
    }
}
