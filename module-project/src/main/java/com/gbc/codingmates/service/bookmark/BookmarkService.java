package com.gbc.codingmates.service.bookmark;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.bookmark.BookmarkRepository;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final ProjectRepository projectRepository;


    //add bookmark if project has not been bookmarked before
    public boolean addBookmark(MemberDto memberDto, ProjectDto projectDto){
        Project project = projectRepository.findById(projectDto.getId()).get();
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

    //check if member has bookmarked this project before
    private boolean isNotAlreadyBookmarked(Long member_id, Project project) {
        return bookmarkRepository.findByMemberIdAndProject(member_id, project).isPresent();
    }
}
