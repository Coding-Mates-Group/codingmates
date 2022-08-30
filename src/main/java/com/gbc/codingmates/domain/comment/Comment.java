package com.gbc.codingmates.domain.comment;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.gbc.codingmates.domain.BaseTimeEntity;
import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.project.Project;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_Comment_Member"))
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_Comment_Project"))
    private Project project;

    private String title;

}
