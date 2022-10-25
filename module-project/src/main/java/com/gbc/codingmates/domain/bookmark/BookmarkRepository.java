package com.gbc.codingmates.domain.bookmark;

import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    Optional<Bookmark> findByMember_idAndProject(MemberDto memberDto, ProjectDto projectDto);
}
