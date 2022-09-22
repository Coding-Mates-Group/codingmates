package com.gbc.codingmates.controller.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.project.ProjectDto;
import com.gbc.codingmates.dto.project.ProjectRequestDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import com.gbc.codingmates.service.project.ProjectService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final Mapper mapper;

    //list all projects
    @GetMapping("")
    public List<ProjectResponseDto> findAll(){
        return projectService.findAll();
    }

    //create project
    @PostMapping("")
    public Long save(@RequestBody final ProjectRequestDto projectRequestDto) {
        return projectService.save(projectRequestDto);

    }

    //edit/update project
    @PatchMapping("/{id}")
    public Long edit(@PathVariable final Long id, @RequestBody final ProjectRequestDto projectRequestDto){
        return projectService.update(id, projectRequestDto);
    }

    //delete project
    @PutMapping("/delete/{id}")
    public void deleteById(@PathVariable final Long id, @RequestBody final ProjectRequestDto projectRequestDto){
        projectService.deleteById(id, projectRequestDto);
    }
}
