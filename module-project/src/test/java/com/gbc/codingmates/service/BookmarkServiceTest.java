package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.dto.BookmarkDto;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.ProjectDto;
import com.gbc.codingmates.service.BookmarkService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class BookmarkServiceTest {

    @Autowired
    BookmarkService bookmarkService;

    @Test
    void addBookmark(MemberDto memberDto, ProjectDto projectDto){
        //given
        Long memberId = memberDto.getMemberId();
        ProjectDto projectDto1 = ProjectDto.builder()
                .id(1L)
                .title("hi")
                .content("testing")
                .views(30L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .recruitmentStatus("complete")
                .email("testing@gmail.com")
                .url("https://discord/hola")
                .build();
        Project project1 = Project.toEntity(projectDto1);
        BookmarkDto bookmarkDto = BookmarkDto.builder()
                .id(1L)
                .member_id(memberId)
                .project(project1)
                .accept_info(true)
                .build();

        //when
        Bookmark bookmark1 = Bookmark.toEntity(bookmarkDto);
        bookmarkService.addBookmark(project1.getId(),memberDto,projectDto1);
    }

}