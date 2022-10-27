package com.gbc.codingmates.controller;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.bookmark.BookmarkRepository;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.service.bookmark.BookmarkService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class BookmarkControllerTest {

    @Autowired ProjectRepository projectRepository;
    @Autowired BookmarkRepository bookmarkRepository;
    @Autowired BookmarkService bookmarkService;

    @Test
    void testCreateBookmark() throws Exception{
        Bookmark bookmark = new Bookmark(new Project(1L, new ArrayList<Long>(), 3L, "HI", "BITCH", "WELP"),
                2L, true);
        bookmarkRepository.save(bookmark);

        Bookmark bookmark1 = bookmarkRepository.findAll().get(0);
        assertNotNull(bookmark1);
        assertNotNull(bookmark1.getMemberId());
        assertNotNull(bookmark1.getProject());
    }
}