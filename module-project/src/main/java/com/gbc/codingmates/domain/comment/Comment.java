package com.gbc.codingmates.domain.comment;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

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
@NoArgsConstructor
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comment_id")
    private Long id;


    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_Comment_Member"))
    private Long member_id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_Comment_Project"))
    private Project project;

    private String title;

}