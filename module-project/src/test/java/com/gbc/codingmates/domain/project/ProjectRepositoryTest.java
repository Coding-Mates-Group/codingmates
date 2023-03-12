package com.gbc.codingmates.domain.project;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    private void saveProject(){
        //given
        Project project = Project.builder()
                .id(1L)
                .title("test")
                .content("testing")
                .email("random@gmail.com")
                .views(3L)
                .recruitmentStatus("OPEN")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .member_id(2L)
                .skillStack(new String[]{"Spring","HTML"})
                .build();

        //when
        Project savedProject = projectRepository.save(project);

        //then
        assertThat(savedProject.getTitle()).isEqualTo("test");

    }

}