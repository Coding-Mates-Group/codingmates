package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.bookmark.BookmarkRepository;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final ProjectRepository projectRepository;

    //add bookmark if project has not been bookmarked before
    public boolean addBookmark(final Long project_id, final MemberDto memberDto, final ProjectDto projectDto){
        Project project = findProjectByProjectId(project_id);
        Long member_id = memberDto.getMemberId();
        if(isNotAlreadyBookmarked(member_id,project)){
            bookmarkRepository.save(Bookmark.builder()
                    .member_id(member_id)
                    .project(project)
                    .accept_info(true)
                    .build());
        }
        return false;
    }

    private Project findProjectByProjectId(final Long project_id) {
        return projectRepository.findById(project_id).get();
    }

    //check if member has bookmarked this project before
    private boolean isNotAlreadyBookmarked(final Long member_id, final Project project) {
        return bookmarkRepository.findByMemberIdAndProject(member_id, project).isPresent();
    }
}
