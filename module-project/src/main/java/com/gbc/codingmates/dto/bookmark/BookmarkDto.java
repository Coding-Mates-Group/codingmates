package com.gbc.codingmates.dto.bookmark;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "DTO for bookmark")
public class BookmarkDto {
    private final Long id;
    private final Project project;
    private final Long member_id;
    private final Boolean accept_info;

    public static BookmarkDto from(Bookmark bookmark){
        return BookmarkDto.builder()
                .id(bookmark.getId())
                .project(bookmark.getProject())
                .member_id(bookmark.getMember_id())
                .accept_info(bookmark.getAccept_info())
                .build();
    }

}
