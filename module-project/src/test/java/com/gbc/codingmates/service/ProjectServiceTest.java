package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.RecruitmentDto;
import com.gbc.codingmates.dto.project.ProjectCreateDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
//@Sql("/db.h2/import.sql")
@ActiveProfiles("test")
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    DataSource dataSource;

    @BeforeAll
    public void init(){
        try(Connection conn = dataSource.getConnection()){
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("db.h2/import.sql"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }


//    @AfterEach
//    public void cleanup(){
//        projectRepository.deleteAll();
//    }

//    @Test
//    @DisplayName("check initial data in db")
//    public void init(){
//        Project project = projectRepository.getOne(0L);
//        System.out.println(project);
//    }

    @Test
    void saveProject() {
        //given
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
                .title("test")
                .content("testing")
                .email("random@gmail.com")
                .url("hola.com")
                .recruitmentDtoList(recruitmentDtoList)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build();

        ProjectCreateDto projectCreateDto = ProjectCreateDto.builder()
                .projectDto(projectDto)
                .recruitmentDtoList(recruitmentDtoList)
                .build();
//        when
        Long project_id = projectService.saveProject(projectCreateDto);
//        Project savedProject = projectRepository.findById(project_id).get();

        //then
//        assertThat(savedProject.getId()).isEqualTo(1);
        assertThat(project_id).isEqualTo(1);
    }

    @Test
    void listAll() {
        //given inital data

        //when
        ProjectDto projectDto = projectService.listAll()
                .stream()
                .findFirst()
                .get();

        //then
        assertThat(projectDto.getTitle().equals("hi"));
        assertThat(projectDto.getContent().equals("testing"));
    }

    @Test
    void edit() {
        //given
//        MemberDto memberDto = MemberDto.builder()
//                .memberId(3L)
//                .username("brian")
//                .memberStatus("BASIC")
//                .gitRepository("https://github.com/2")
//                .
//                .build()
//        assertThat(projectRepository.findByTitle("testing ").isEmpty());

    }

    @Test
    void deleteById() {
    }

    @Test
    void findProjectById(){

    }

    @Test
    void checkEditPermission(){

    }

    @Test
    void validateDuplicateProject(){

    }



}