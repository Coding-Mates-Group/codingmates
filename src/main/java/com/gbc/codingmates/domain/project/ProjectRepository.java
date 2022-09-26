package com.gbc.codingmates.domain.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("select p from Project p where p.title = :title")
    List<Project> findByTitle(String title);
}
