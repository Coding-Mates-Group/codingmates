package com.gbc.codingmates.domain.project;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

//import module-member.
import com.gbc.codingmates.domain.BaseTimeEntity;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;

import com.gbc.codingmates.domain.bookmark.Bookmark;
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

    @OneToMany(mappedBy = "member_id", cascade = CascadeType.ALL)
    Set<Bookmark> bookmarks = new HashSet<>();

    @Email(message = "Please enter a valid email")
    private Email email;

    @Column(nullable = false)
    private Integer recruitCount;

    @Column(nullable = true)
    private String url;

    //    @Builder(builderClassName = "createPostWithAll", builderMethodName = "createPostWithAll")
    @Builder
    public Project(Long id, String title, String content, Blob contentBig, Long views, LocalDateTime startDate, LocalDateTime endDate,
                   String recruitmentStatus, Long member_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.contentBig = contentBig;
        this.views = views;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitmentStatus = recruitmentStatus;
        this.member_id = member_id;
    }


    public Project(Long memberId, List<Long> skillIds ,Long projectId, String title, String content, String recruitmentStatus) {
        super();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifyToot = LocalDateTime.now();
    }

    public void updateView(Long views) {
        this.views = views;
    }
}
