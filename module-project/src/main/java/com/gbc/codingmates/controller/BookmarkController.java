package com.gbc.codingmates.controller;

import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import com.gbc.codingmates.service.bookmark.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping("/{id}")
    public ResponseEntity<String> addBookmark(MemberDto memberDto, ProjectDto projectDto){

        boolean result = bookmarkService.addBookmark(memberDto, projectDto);
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
