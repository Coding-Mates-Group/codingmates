package com.gbc.codingmates.controller.project;

import com.gbc.codingmates.dto.MemberDto;
import com.gbc.codingmates.dto.project.ProjectRequestDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import com.gbc.codingmates.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    //create project
    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody final ProjectRequestDto projectRequestDto) {
        return projectService.save(projectRequestDto);
    }

    //list all projects
    @GetMapping("")
    public ResponseEntity<List<ProjectRequestDto>> findAll(){
        return projectService.findAll();
    }

    //edit/update project
//    @PatchMapping("/{id}")
    @PutMapping("{id}")
    public ResponseEntity<Long> edit(@PathVariable final Long id, @RequestBody final ProjectRequestDto projectRequestDto){
        return projectService.update(id, projectRequestDto);
    }

    //delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable final Long id){
        return projectService.deleteById(id);
    }

    //search project by title
    @GetMapping("/search/{title}")
    public ResponseEntity<List<ProjectRequestDto>> searchProjectByTitle(@PathVariable final String title, @RequestBody ProjectRequestDto projectRequestDto){
        return projectService.searchProjectByTitle(title);
    }
}
