package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.project.CustomProjectRepositoryImpl;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.ProjectDto;
import com.gbc.codingmates.exception.CustomException;
import com.gbc.codingmates.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CustomProjectRepositoryImpl customProjectRepository;

//    Create and save project
    @Transactional
    public Long saveProject(final ProjectDto projectDto){
        validateDuplicateProject(projectDto);
        Project project = projectRepository.save(Project.toEntity(projectDto));
        return project.getId();
    }

    //list all projects
    public List<ProjectDto> listAll(){
//        return customProjectRepository.listAllWithMember();
        return projectRepository.findAll()
                .stream()
                .map(Project::from)
                .collect(Collectors.toList());
    }

    //update/edit project's title and content
    @Transactional
    public Long edit(final Long id, final MemberDto memberDto, final ProjectDto ProjectDto) throws CustomException {
        Project project = findProjectById(id);
        checkEditPermission(project, memberDto);
        projectRepository.save(project);
        return id;
    }

    //delete project
    @Transactional
    public Long deleteById(final Long id, final MemberDto memberDto) throws CustomException {
        Project project = findProjectById(id);
        checkEditPermission(project, memberDto);
        projectRepository.delete(project);
        return id;
    }

    //returns page of 21 projects 
    public List<Project> paging(){
        return customProjectRepository.paging();
    }

    //find project by project_id
    private Project findProjectById(Long id) {
        Project project;
        try {
            project = projectRepository.findById(id).get();
        } catch (CustomException e) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER);
        }
        return project;
    }

    //check if person is owner of this project post and if he/she is authorised to edit
    private void checkEditPermission(Project project, MemberDto memberDto){
        Long ownerId = project.getMember_id();
        if(ownerId!= memberDto.getMemberId()){
            throw new CustomException(ErrorCode.PROJECT_EDIT_PERMISSION_FORBIDDEN);
        }
    }

    //check existing, duplicate project via title in case of abusers who spam projects
    private void validateDuplicateProject(ProjectDto ProjectDto){
        List<Project> findProjects = projectRepository.findByTitle(ProjectDto.getTitle());
        if(!findProjects.isEmpty()){
            throw new CustomException(ErrorCode.ALREADY_EXISTING_PROJECT);
        }
    }
}
