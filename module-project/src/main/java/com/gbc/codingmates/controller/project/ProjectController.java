package com.gbc.codingmates.controller.project;

import com.gbc.codingmates.dto.project.ProjectRequestDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import com.gbc.codingmates.service.project.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    //list all projects
    @GetMapping("")
    public List<ProjectResponseDto> findAll() {
        return projectService.findAll();
    }

    //create project
    @PostMapping("")
    public Long save(@RequestBody final ProjectRequestDto projectRequestDto) {
        return projectService.save(projectRequestDto);
    }

    //edit/update project
    @PatchMapping("/{id}")
    public Long edit(@PathVariable final Long id,
        @RequestBody final ProjectRequestDto projectRequestDto) {
        return projectService.update(id, projectRequestDto);
    }

    //delete project
    @PutMapping("/delete/{id}")
    public void deleteById(@PathVariable final Long id,
        @RequestBody final ProjectRequestDto projectRequestDto) {
        projectService.deleteById(id, projectRequestDto);
    }
}
