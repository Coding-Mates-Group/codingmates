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
    private final ModelMapper modelMapper;

    public boolean addBookmark(MemberDto memberDto, ProjectDto projectDto){
        Project project = projectRepository.findById(projectDto.getId()).get();
//        if(optionalProject.isPresent()){
//            Project project = optionalProject.get();
//        }
        if(isNotAlreadyBookmarked(memberDto, project)){
            bookmarkRepository.save(new Bookmark(project,memberDto));
            return true;
        }
        return false;
    }
    //check if member already bookmarked
    private boolean isNotAlreadyBookmarked(MemberDto memberDto, Project project) {
        return bookmarkRepository.findByMember_idAndProject(memberDto, modelMapper.map(project, ProjectDto.class)).isEmpty();
    }

}
