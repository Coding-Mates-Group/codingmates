package com.gbc.codingmates.domain.project;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectServiceTest {

//    @Autowired
//    private ProjectService projectService;
//
////    @AfterEach
////    public void cleanup(){
////        projectRepository.deleteAll();
////    }
//
//    @Test
//    public void saveProject(){
//        //given
//        ProjectDto projectDto = ProjectDto.builder()
//                .id(1L)
//                .title("hi")
//                .content("testing")
//                .views(30L)
//                .startDate(LocalDateTime.now())
//                .endDate(LocalDateTime.now())
//                .recruitmentStatus("complete")
////                .email("testing@gmail.com")
//                .url("https://discord/hola")
//                .build();
//
//        //when
//        projectService.saveProject(projectDto);
//
//        //then
//        assertThat(projectDto.getTitle().equals("hi"));
//        assertThat(projectDto.getContent().equals("testing"));
//
//    }
}