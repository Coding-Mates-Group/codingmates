package com.gbc.codingmates.dto;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "DTO for bookmark")
public class BookmarkDto {
    private final Long id;
    private final Project project;
    private final Long member_id;
    private final Boolean accept_info;

}
