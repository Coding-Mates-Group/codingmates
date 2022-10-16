package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.service.project.ProjectService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    //    @AfterEach
//    public void cleanup(){
//        projectRepository.deleteAll();
//    }
//    @Test
//    public void saveAndFindAll(){
//        //given
//        LocalDateTime now = LocalDateTime.now();
//        projectService.save(ProjectDto.builder()
//                .id(1L)
//                .title("hi")
//                .content("testing")
//                .views(30L)
//                .recruitmentStatus("complete").build());
//
//        //when
//        ResponseEntity<List<ProjectDto>> all = projectService.findAll();
//
//        //then
//        ProjectDto ProjectDto = all.getBody().get(0);
//        assertThat(ProjectDto.getTitle().equals("hi"));
//        assertThat(ProjectDto.getContent().equals("testing"));
//
//    }
}