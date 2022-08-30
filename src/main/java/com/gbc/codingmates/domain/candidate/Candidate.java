package com.gbc.codingmates.domain.candidate;

import com.gbc.codingmates.domain.BaseTimeEntity;
import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.project.Project;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Candidate extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="candidate_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_candidate_member_can"))
    private Member member_can;

    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name= "FK_candidate_project_can"))
    private Project project_can;

    @Column(nullable = false)
    private String result;
}
