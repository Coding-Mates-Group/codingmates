package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.dto.project.ProjectDto;
import com.gbc.codingmates.dto.project.ProjectRequestDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import com.gbc.codingmates.service.project.ProjectService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectService projectService;

    //    @AfterEach
//    public void cleanup(){
//        projectRepository.deleteAll();
//    }
    @Test
    public void saveAndFindAll(){
        //given
        LocalDateTime now = LocalDateTime.now();
        projectService.save(ProjectRequestDto.builder()
                .id(1L)
                .title("hi")
                .content("testing")
                .views(30L)
                .recruitmentStatus("complete").build());

//        projectService.save(Project.builder()
//                .title("hi")
//                .content("testing")
//                .views(30L)
//                .recruitmentStatus("complete")
//                .build());

        //when
        ResponseEntity<List<ProjectRequestDto>> all = projectService.findAll();

        //then
        ProjectRequestDto projectRequestDto = all.getBody().get(0);
        assertThat(projectRequestDto.getTitle().equals("hi"));
        assertThat(projectRequestDto.getContent().equals("testing"));

    }
}