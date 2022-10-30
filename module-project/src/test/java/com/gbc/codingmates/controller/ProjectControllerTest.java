package com.gbc.codingmates.controller;

import com.gbc.codingmates.dto.ProjectDto;
import com.gbc.codingmates.service.ProjectService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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
                .recruitmentStatus("complete")
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