package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.domain.recruitment.RecruitmentRepository;
import com.gbc.codingmates.dto.project.ProjectCreateDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Test
    public void saveProject(){
        //given
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

        Set<Bookmark> bookmarkSet = Stream.of(Bookmark.builder()
                .accept_info(true)
                .project(project)
                .member_id(2L)
                .build())
                .collect(Collectors.toSet());

        List<Recruitment> recruitmentList = Arrays.asList(
                Recruitment.builder()
                        .recruitmentCount(3)
                        .project_recr(project)
                        .recruitmentType("backend")
                        .recruitmentStatus("open")
                        .build(),
                Recruitment.builder()
                        .recruitmentCount(1)
                        .project_recr(project)
                        .recruitmentType("frontend")
                        .recruitmentStatus("open")
                        .build());
        Recruitment rec = Recruitment.builder()
                .recruitmentCount(3)
                .project_recr(project)
                .recruitmentType("backend")
                .recruitmentStatus("open")
                .build();

        //when
//        project.getRecruitmentList().add(recruitmentList);
//        project.getBookmarkSet().add(bookmarkSet);
//        project.setBookmarkSet(bookmarkSet);
//        project.setRecruitmentList(recruitmentList);
        Project savedProject = projectRepository.save(project);
        savedProject.setRecruitmentList(rec);
//        Recruitment savedRec = recruitmentRepository.save(rec);
//        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);

        //then
        assertThat(savedProject.getTitle()).isEqualTo("test");
//        assertThat(savedRec.getRecruitmentCount()).isEqualTo(3);
//        assertThat(savedRec.getProject_recr().getMember_id()).isEqualTo(2L);
//        assertThat(savedProject.getRecruitmentList().size()).isEqualTo(2);
        assertThat(savedProject.getRecruitmentList().get(0).getRecruitmentCount()).isEqualTo(3);
//        assertThat(savedRecruitment.getProject_recr().getTitle()).isEqualTo("test");
    }

    @Test
    public void saveProjectWithDto(){
        //given
        ProjectCreateDto projectDto = Project.from(ProjectCreateDto
                .builder()
                .member_id(2L)
                .title("test")
                .content("testing")
                .email("random@gmail.com")
                .views(3L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .url("hola.com")
                .build());

    }

}