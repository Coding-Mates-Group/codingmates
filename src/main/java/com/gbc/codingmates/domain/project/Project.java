package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.domain.BaseTimeEntity;
import com.gbc.codingmates.domain.CommentsManager;
import com.gbc.codingmates.domain.comment.Comment;
import com.gbc.codingmates.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Project extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private CommentsManager commentsManager = new CommentsManager();

    @Column(nullable = false)
    private String title;

    @Column
    private String category;

    private LocalDateTime startDate, dueDate;

    public List<Comment> getComments(){
        return commentsManager.getComments();
    }
}
