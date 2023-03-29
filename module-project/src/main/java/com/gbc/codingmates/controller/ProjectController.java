package com.gbc.codingmates.controller;

import com.gbc.codingmates.annotation.JwtMemberInfo;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.ProjectDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import com.gbc.codingmates.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;


@RestController
@Validated
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    //create project
    @ApiOperation(value = "create Project post")
    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid final ProjectDto projectDto,
                                     BindingResult bindingResult) {
        ResponseEntity<BindingResult> checkViaBindingResult = checkViaBindingResult(bindingResult);
        if (checkViaBindingResult != null) return checkViaBindingResult;
        projectService.saveProject(projectDto);
        return ResponseEntity.ok(projectDto.getId());
    }

    //check via binding result
    private ResponseEntity<BindingResult> checkViaBindingResult(BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return null;
    }

    //list all projects
    @ApiOperation(value = "list of all projects")
    @GetMapping("")
    public ResponseEntity<List<ProjectDto>> listAll(){
        return ResponseEntity.ok(projectService.listAll());
    }

    //output 1 project when u click on homepage
    @ApiOperation(value = "output details of 1 project as response when click on homepage")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> listOne(){
        return null;
    }

    //edit/update project
//    @PatchMapping("/{id}")
    @PutMapping("{id}")
    public ResponseEntity<Long> edit(@PathVariable final Long id, @JwtMemberInfo @Valid MemberDto memberDto,
                                     @RequestBody @Valid final ProjectDto ProjectDto, BindingResult bindingResult) throws Exception {
        return ResponseEntity.ok(projectService.edit(id, memberDto, ProjectDto));
    }



    //delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable final Long id, @JwtMemberInfo @Valid MemberDto memberDto,
                                           BindingResult bindingResult) throws Exception {
        return ResponseEntity.ok(projectService.deleteById(id, memberDto));
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
