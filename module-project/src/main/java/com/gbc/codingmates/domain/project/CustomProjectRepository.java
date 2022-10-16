package com.gbc.codingmates.domain.project;

import java.util.List;

public interface CustomProjectRepository {
    List<Project> findAllWithMember();
}
