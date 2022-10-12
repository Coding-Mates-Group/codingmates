package com.gbc.codingmates.controller.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.dto.project.ProjectDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import com.gbc.codingmates.service.project.ProjectService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    //create project
    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody final ProjectDto ProjectDto) {
        return projectService.save(ProjectDto);
    }

    //list all projects
    @GetMapping("")
    public ResponseEntity listAll(){
        List<Project> projects = projectService.listAll();
        List<ProjectDto> result = projects.stream()
                .map(ProjectDto::new)
                .collect(Collectors.toList());
        Result<List<ProjectDto>> listResult = new Result<>(result);
        return ResponseEntity.ok(listResult);
    }

    //edit/update project
//    @PatchMapping("/{id}")
    @PutMapping("{id}")
    public ResponseEntity<Long> edit(@PathVariable final Long id, @RequestBody final ProjectDto ProjectDto){
        return projectService.update(id, ProjectDto);
    }

    //delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable final Long id){
        return projectService.deleteById(id);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    static class createProjectRequest {
        private Long id;
        private String title;
        private String content;
        private Long views;
        private String recruitmentStatus;
        private String username;
    }

    @Data
    @AllArgsConstructor
    static class createProjectResponse{
        private Long id;
    }
}
