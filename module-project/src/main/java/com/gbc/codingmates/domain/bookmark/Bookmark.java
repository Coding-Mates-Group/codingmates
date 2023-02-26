package com.gbc.codingmates.domain.bookmark;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.dto.BookmarkDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {
    @Id
    @Column(name = "bookmark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_BookmarkProject"))
    private Project project;

    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_BookmarkMember"))
    private Long memberId;

    @Column(nullable = false)
    private Boolean accept_info;

    public static Bookmark toEntity(BookmarkDto bookmarkDto){
        return Bookmark.builder()
                .id(bookmarkDto.getId())
                .project(bookmarkDto.getProject())
                .member_id(bookmarkDto.getMember_id())
                .accept_info(bookmarkDto.getAccept_info())
                .build();
    }

    public static BookmarkDto from(Bookmark bookmark){
        return BookmarkDto.builder()
                .id(bookmark.getId())
                .project(bookmark.getProject())
                .member_id(bookmark.getMemberId())
                .accept_info(bookmark.getAccept_info())
                .build();
    }

    @Builder
    public Bookmark(Long id, Project project, Long member_id, Boolean accept_info){
        this.id = id;
        this.project = project;
        this.memberId = member_id;
        this.accept_info = accept_info;
    }
}
