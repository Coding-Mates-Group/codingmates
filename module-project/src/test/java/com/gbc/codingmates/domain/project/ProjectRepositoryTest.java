package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.domain.recruitment.RecruitmentRepository;
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

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Test
    private void saveProject(){
        //given
        Recruitment recruitment = Recruitment.builder()
                .id(1L)
                .recruitmentCount(3)
                .recruitmentType("backend")
                .recruitmentStatus("open")
                .project_recr(
                        Project.builder()
                                .id(1L)
                                .member_id(2L)
                                .title("test")
                                .content("testing")
                                .email("random@gmail.com")
                                .views(3L)
                                .startDate(LocalDateTime.now())
                                .endDate(LocalDateTime.now())
                                .url("hola.com")
                                .build()
                )
                .build();

        Project project = Project.builder()
                .id(1L)
                .member_id(2L)
                .title("test")
                .content("testing")
                .email("random@gmail.com")
                .views(3L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .url("hola.com")
                .build();

        //when
        Project savedProject = projectRepository.save(project);
        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);

        //then
        assertThat(savedProject.getTitle()).isEqualTo("test");
        assertThat(savedRecruitment.getProject_recr().getTitle()).isEqualTo("test");

    }

}