package com.gbc.codingmates.controller;

import com.gbc.codingmates.annotation.JwtMemberInfo;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import com.gbc.codingmates.service.project.ProjectService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    //create project
    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid final ProjectDto projectDto,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return projectService.saveProject(projectDto);
    }

    //list all projects
    @GetMapping("")
    public ResponseEntity listAll(){
        return projectService.listAll();
    }

    //edit/update project
//    @PatchMapping("/{id}")
    @PutMapping("{id}")
    public ResponseEntity<Long> edit(@JwtMemberInfo MemberDto memberDto, @PathVariable final Long id,
                                     @RequestBody @Valid final ProjectDto ProjectDto) throws AccessDeniedException {
        return projectService.edit(memberDto, id, ProjectDto);
    }

    //delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@JwtMemberInfo MemberDto memberDto, @PathVariable final Long id) throws AccessDeniedException {
        return projectService.deleteById(memberDto, id);
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
