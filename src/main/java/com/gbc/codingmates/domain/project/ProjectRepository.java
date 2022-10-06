package com.gbc.codingmates.domain.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("select p from Project p where p.title = :title")
    List<Project> findByTitle(@Param("title") String title);

    List<Project> findByTitleContaining(String title);
}
