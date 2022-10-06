package com.gbc.codingmates.dto.comment;

import com.gbc.codingmates.domain.comment.Comment;
import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.project.Project;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String createdDate;
    private String modifiedDate;
    private String nickname;
    private Long projectId;

    //entity to dto
    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = String.valueOf(comment.getCreatedDate());
        this.modifiedDate = String.valueOf(comment.getModifiedDate());
        //to be changed to nickname
        this.nickname = comment.getMember().getUsername();
        this.projectId = comment.getProject().getId();
    }
}
