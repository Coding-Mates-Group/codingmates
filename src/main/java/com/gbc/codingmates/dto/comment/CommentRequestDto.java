package com.gbc.codingmates.dto.comment;

import com.gbc.codingmates.domain.comment.Comment;
import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.project.Project;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentRequestDto {
    private Long id;
    private String comment;
    private String createdDate;
    private String modifiedDate;
    private Member member;
    private Project project;

    public Comment toEntity(){
        Comment comments = Comment.builder()
                .id(id)
                .comment(comment)
                .member(member)
                .project(project)
                .build();
        return comments;
    }

//    @Builder
//    public CommentRequestDto commentRequestDto(Long id, String comment, String createdDate, String modifiedDate, Member member,
//                                        Project project){
//        this.id=id;
//        this.comment = comment;
//        this.createdDate = createdDate;
//        this.modifiedDate = modifiedDate;
//        this.member = member;
//        this.project = project;
//    }
}
