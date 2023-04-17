package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.domain.bookmark.Bookmark;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.domain.recruitment.RecruitmentRepository;
import com.gbc.codingmates.dto.RecruitmentDto;
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

        //when
        Project savedProject = projectRepository.save(project);
//        savedProject.addRecruitment(rec);

        //then
        assertThat(savedProject.getTitle()).isEqualTo("test");
//        assertThat(savedProject.getRecruitmentList().get(0).getRecruitmentCount()).isEqualTo(3);
    }

    @Test
    public void saveProjectWithDto(){
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

        //when
        Project project = Project.toEntity(projectDto);
        List<Recruitment> recruitmentList = Recruitment.toEntityList(recruitmentDtoList);
//        recruitmentList.forEach(recruitment -> project.addRecruitment(recruitment));
        Project savedProject = projectRepository.save(project);

        //then
//        assertThat(savedProject.getRecruitmentList().get(0).getRecruitmentCount()).isEqualTo(3);
        assertThat(savedProject.getContent()).isEqualTo("testing");
    }

}