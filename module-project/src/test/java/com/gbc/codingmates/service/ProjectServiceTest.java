package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.ProjectDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
//@Transactional
@SpringBootTest
@ActiveProfiles("test")
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;



//    @AfterEach
//    public void cleanup(){
//        projectRepository.deleteAll();
//    }

    @Test
    void saveProject() {
        //given
        ProjectDto projectDto = ProjectDto.builder()
                .id(1L)
                .title("hi")
                .content("testing")
                .views(30L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .recruitmentStatus("complete")
                .email("testing@gmail.com")
                .url("https://discord/hola")
                .build();

        //when
        projectService.saveProject(projectDto);

        //then
        assertThat(projectDto.getTitle().equals("hi"));
        assertThat(projectDto.getContent().equals("testing"));

    }

    @Test
    void listAll() {
        ResponseEntity<List<ProjectDto>> listResponseEntity = projectService.listAll();

    }

    @Test
    void findById() {
    }

    @Test
    void edit() {
    }

    @Test
    void deleteById() {
    }
}