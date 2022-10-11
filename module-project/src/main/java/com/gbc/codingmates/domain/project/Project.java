package com.gbc.codingmates.domain.project;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gbc.codingmates.domain.BaseTimeEntity;
import java.sql.Blob;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @JoinColumn(name = "member_id")
    private Long member_id;

    @Column(nullable = false)
    private String title;

    @Column
    private String category;

    @Column(nullable = false)
    private String content;

    @Lob
    @Column
//    @Column(nullable = false)
    private Blob contentBig;

    private Long views;

    private LocalDateTime startDate, endDate;

    private LocalDateTime modifyToot;

    private String recruitmentStatus;

    //    @Builder(builderClassName = "createPostWithAll", builderMethodName = "createPostWithAll")
    @Builder
    public Project(String title, String content, Blob contentBig, Long views,
        LocalDateTime startDate, LocalDateTime endDate,
        String recruitmentStatus, Long member_id) {
        this.title = title;
        this.content = content;
        this.contentBig = contentBig;
        this.views = views;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitmentStatus = recruitmentStatus;
        this.member_id = member_id;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifyToot = LocalDateTime.now();
    }
}