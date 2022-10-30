package com.gbc.codingmates.domain.bookmark;

import com.gbc.codingmates.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    Optional<Bookmark> findByMemberIdAndProject(Long memberId, Project project);
}
