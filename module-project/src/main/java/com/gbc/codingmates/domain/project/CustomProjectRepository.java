package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.dto.project.ProjectResponseDto;

import java.util.List;

public interface CustomProjectRepository {
//    List<Project> listAllWithMember();

    ProjectResponseDto listOne();
}
