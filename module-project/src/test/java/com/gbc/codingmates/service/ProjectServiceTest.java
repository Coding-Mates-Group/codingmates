package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.project.ProjectRepository;
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

//        when
        projectService.saveProject(projectDto);

        //then
        assertThat(projectDto.getTitle().equals("hi"));
        assertThat(projectDto.getContent().equals("testing"));

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
        assertThat(projectRepository.findByTitle("testing ").isEmpty());

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