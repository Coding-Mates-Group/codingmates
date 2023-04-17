package com.gbc.codingmates.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gbc.codingmates.dto.RecruitmentDto;
import com.gbc.codingmates.dto.project.ProjectCreateDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectController;

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;


    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(projectController)
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void saveSingleProject() throws Exception {
        //given
        final String url = "/project";
        List<RecruitmentDto> recruitmentDtoList = Arrays.asList(
                RecruitmentDto.builder()
                        .recruitmentCount(3)
                        .recruitmentType("backend")
                        .recruitmentStatus("open")
                        .build(),
                RecruitmentDto.builder()
                        .recruitmentCount(1)
                        .recruitmentType("frontend")
                        .recruitmentStatus("open")
                        .build());

        ProjectDto projectDto = ProjectDto.builder()
                .title("testeat")
                .content("testing")
                .email("random@gmail.com")
                .url("hola.com")
                .recruitmentDtoList(recruitmentDtoList)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build();

//        List<ProjectDto> projectDtos = Arrays.asList(projectDto);
        ProjectCreateDto projectCreateDto = ProjectCreateDto.builder()
                .projectDto(projectDto)
                .recruitmentDtoList(recruitmentDtoList)
                .build();
        String body = objectMapper.writeValueAsString(projectCreateDto);

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk());
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