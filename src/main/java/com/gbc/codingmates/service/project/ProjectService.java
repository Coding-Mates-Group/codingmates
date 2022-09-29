package com.gbc.codingmates.service.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.project.ProjectRequestDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//        return project.getId();
        return ResponseEntity.ok(project.getId());
    }

//    find one project
//    public ResponseEntity<Project> findProject(final Long id, final ProjectRequestDto projectRequestDto){
//
//    }


    //List all projects
    public ResponseEntity<List<ProjectResponseDto>> findAll(){
        List<Project> list = projectRepository.findAll();
        return  ResponseEntity.ok(list.stream().map(ProjectResponseDto::new).collect(Collectors.toList()));
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
    public void deleteById(final Long id, final ProjectRequestDto projectRequestDto){
//        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        projectRepository.deleteById(id);
    }

    @Transactional
    public void updateView(final Long id, final ProjectRequestDto projectRequestDto){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalStateException("There is no such project found"));
        project.updateView(projectRequestDto.getViews());

    }

    //check existing, duplicate project
    private void validateDuplicateProject(ProjectRequestDto projectRequestDto){
        List<Project> findProjects = projectRepository.findByTitle(projectRequestDto.getTitle());
        if(!findProjects.isEmpty()){
            throw new IllegalStateException("already an existing project");
        }
    }
}
