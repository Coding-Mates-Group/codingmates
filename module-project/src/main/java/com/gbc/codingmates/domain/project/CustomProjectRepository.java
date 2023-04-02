package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.dto.project.response.ProjectSelectOneResponseDto;

public interface CustomProjectRepository {
//    List<Project> listAllWithMember();

    ProjectSelectOneResponseDto listOne();
}
