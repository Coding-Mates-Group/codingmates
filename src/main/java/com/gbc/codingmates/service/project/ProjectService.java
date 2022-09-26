package com.gbc.codingmates.service.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.project.ProjectDto;
import com.gbc.codingmates.dto.project.ProjectRequestDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    //Create project
    @Transactional
    public Long save(final ProjectRequestDto projectRequestDto){
        Project project = projectRepository.save(projectRequestDto.toEntity());
        return project.getId();
    }

    //List all projects
    public List<ProjectResponseDto> findAll(){
        List<Project> list = projectRepository.findAll();
        return list.stream().map(ProjectResponseDto::new).collect(Collectors.toList());
    }

    //update/edit project
    @Transactional
    public Long update(final Long id, final ProjectRequestDto projectRequestDto){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        project.update(projectRequestDto.getTitle(), projectRequestDto.getContent());
        return id;
    }

    //delete project
    public void deleteById(final Long id, final ProjectRequestDto projectRequestDto){
//        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        projectRepository.deleteById(id);
    }


}
