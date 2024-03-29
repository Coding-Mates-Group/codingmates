package com.gbc.codingmates.domain.comment;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.gbc.codingmates.domain.BaseTimeEntity;


import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Entity
//@NoArgsConstructor
//@Getter
//public class Comment extends BaseTimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "comment_id")
//    private Long id;
//
//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String comment;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_Comment_Member"))
//    private Long member_id;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_Comment_Project"))
//    private Project project;
//
//    @Builder
//    public Comment(Long id, String comment, Long member_id, Project project){
//        this.id=id;
//        this.comment=comment;
//        this.member_id = member_id;
//        this.project = project;
//    }
//}
