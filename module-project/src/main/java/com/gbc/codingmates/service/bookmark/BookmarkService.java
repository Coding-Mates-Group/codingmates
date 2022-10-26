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

    public boolean addBookmark(MemberDto memberDto, ProjectDto projectDto){
        Project project = projectRepository.findById(projectDto.getId()).get();
        Long member_id = memberDto.getMemberId();
        Boolean accept_info = true;

        Bookmark bookmark = bookmarkRepository.findByMemberIdAndProject(member_id, project).get();

//        if(project.isPresent()){
//            Project project = optionalProject.get();
//        }
//        if(isNotAlreadyBookmarked(memberDto, )){
//            bookmarkRepository.save(new Bookmark(project,memberDto));
//            return true;
//        }
//        bookmarkRepository.save(new Bookmark(project, member_id, accept_info));
        bookmarkRepository.save(bookmark);
        return false;
    }
//    check if member already bookmarked
//    private boolean isNotAlreadyBookmarked(MemberDto memberDto, ProjectDto projectDto) {
//        return bookmarkRepository.findByMember_idAndProject(memberDto, projectDto).isEmpty();
//    }

}
