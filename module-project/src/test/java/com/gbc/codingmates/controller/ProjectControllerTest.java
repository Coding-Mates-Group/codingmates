package com.gbc.codingmates.controller;

import com.gbc.codingmates.dto.project.ProjectDto;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProjectControllerTest {

    private MockMvc mockMvc;

    @Autowired
    ProjectController projectController;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    void save() {
        //given
        ProjectDto projectDto = ProjectDto.builder()
                .id(1L)
                .title("hi")
                .content("testing")
                .views(30L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
//                .recruitmentStatus("complete")
                .email("testing@gmail.com")
                .url("https://discord/hola")
                .build();

        //when

        //then

    }

    @Test
    void listAll() {
    }

    @Test
    void edit() {
    }

    @Test
    void deleteById() {
    }
}