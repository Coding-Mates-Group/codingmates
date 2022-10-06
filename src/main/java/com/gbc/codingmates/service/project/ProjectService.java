package com.gbc.codingmates.service.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.project.ProjectRequestDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;

    //Create project
    @Transactional
    public ResponseEntity<Long> save(final ProjectRequestDto projectRequestDto){
        validateDuplicateProject(projectRequestDto);
        Project project = projectRepository.save(projectRequestDto.toEntity());
        return ResponseEntity.ok(project.getId());
    }

    //List all projects
    public ResponseEntity<List<ProjectRequestDto>> findAll(){
        List<Project> list = projectRepository.findAll();
//        return list.stream().map(ProjectRequestDto::toEntity).collect(Collectors.toList());
        List<ProjectRequestDto> projectRequestDtoList = new ArrayList<>();
        for(Project project: list){
            ProjectRequestDto projectRequestDto = ProjectRequestDto.builder()
                    .id(project.getId())
                    .title(project.getTitle())
                    .content(project.getContent())
                    .views(project.getViews())
                    .recruitmentStatus(project.getRecruitmentStatus())
                    .build();
            projectRequestDtoList.add(projectRequestDto);
        }
        return ResponseEntity.ok().body(projectRequestDtoList);
    }

    public ResponseEntity<Long> findById(final ProjectRequestDto projectRequestDto){
        Project project = projectRepository.findById(projectRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok(project.getId());
    }

    //update/edit project
    @Transactional
    public ResponseEntity<Long> update(final Long id, final ProjectRequestDto projectRequestDto){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        project.update(projectRequestDto.getTitle(), projectRequestDto.getContent());
        return ResponseEntity.ok(id);
    }

    //delete project
    @Transactional
    public ResponseEntity<Long> deleteById(final Long id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such project"));
        projectRepository.delete(project);
        return ResponseEntity.ok(id);
    }

    //to be changed to ResponseEntity
    //check existing, duplicate project
    private void validateDuplicateProject(ProjectRequestDto projectRequestDto){
        List<Project> findProjects = projectRepository.findByTitle(projectRequestDto.getTitle());
        if(!findProjects.isEmpty()){
            throw new IllegalStateException("already an existing project");
        }
    }

    //search project by title
    public ResponseEntity<List<ProjectRequestDto>> searchProjectByTitle(String title){
        List<Project> projects = projectRepository.findByTitle(title);
        List<ProjectRequestDto> projectRequestDtoList = new ArrayList<>();

        if(projects.isEmpty()) return ResponseEntity.notFound().build();
        for(Project project: projects){
            projectRequestDtoList.add(this.convertEntityToDto(project));
        }
        return ResponseEntity.ok(projectRequestDtoList);
    }

    private ProjectRequestDto convertEntityToDto(Project project){
        return ProjectRequestDto.builder()
                .id(project.getId())
                .title(project.getTitle())
                .content(project.getContent())
                .views(project.getViews())
                .recruitmentStatus(project.getRecruitmentStatus())
                .build();
    }
}
