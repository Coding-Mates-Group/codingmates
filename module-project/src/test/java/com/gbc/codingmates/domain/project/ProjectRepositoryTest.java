package com.gbc.codingmates.domain.project;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;

    private void saveProject(){

        //given
        Project project = Project.builder()
                .id(1L)
                .content("testing")
                .email("random@gmail.com")
                .views(3L)
                .recruitmentStatus("OPEN")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .member_id(2L)
                .build();

        //when
        projectRepository.save(project);

        //then

    }

}