package com.gbc.codingmates.dto;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "DTO for bookmark")
public class BookmarkDto {
    private final Long id;

    @NotEmpty
    private final Project project;

    @NotEmpty
    private final Long member_id;

    @NotEmpty
    private final Boolean accept_info;
}
