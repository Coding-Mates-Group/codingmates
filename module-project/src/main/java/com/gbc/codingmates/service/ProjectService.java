package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.project.CustomProjectRepositoryImpl;
import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.dto.RecruitmentDto;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.project.ProjectCreateDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;

//    Create and save project
//    @Transactional
//    public Long saveProject(final ProjectDto projectDto, final List<RecruitmentDto> recruitmentDtoList){
//        validateDuplicateProject(projectDto,recruitmentDtoList);
//        Project project = Project.toEntity(projectDto);
//        List<Recruitment> recruitmentList = Recruitment.toEntityList(recruitmentDtoList);
//        recruitmentList.forEach(recruitment -> project.addRecruitment(recruitment));
//        Project savedProject = projectRepository.save(project);
//        return savedProject.getId();
//    }

    @Transactional
    public Long saveProject(final ProjectCreateDto projectCreateDto){
        validateDuplicateProject(projectCreateDto);
        Project project = Project.toEntity(projectCreateDto.getProjectDto());
        Project savedProject = projectRepository.save(project);
        return savedProject.getId();
    }

    //list all projects
    public List<ProjectDto> listAll(){
//        return customProjectRepository.listAllWithMember();
        return projectRepository.findAll()
                .stream()
                .map(project -> Project.from(project))
                .collect(Collectors.toList());
    }

    //select one from list of projects in home page
    public ProjectDto selectOne(Long id){
        Optional<Project> optionalProject = projectRepository.findById(id);
//        if(!optionalProject.isPresent()){
//
//        }
        Project project = optionalProject.get();
        return Project.from(project);
    }


//    public List<ProjectDto> listAll(){
//        List<Project> projectList = projectRepository.findAll();
//        return projectList.stream()
//                .map(project -> Project.from(project))
//                .collect(Collectors.toList());
//    }

    //update/edit project's title and content
    @Transactional
    public Long edit(final Long id, final MemberDto memberDto, final ProjectDto projectDto) throws Exception {
        Project project = findProjectById(id, new IllegalArgumentException());
        checkEditPermission(project, memberDto);
        project.update(projectDto.getTitle(), projectDto.getContent(), projectDto.getEmail(), projectDto.getUrl());
        return id;
    }

    //delete project
    @Transactional
    public Long deleteById(final Long id, final MemberDto memberDto) throws Exception {
        Project project = findProjectById(id, new IllegalArgumentException());
        checkEditPermission(project, memberDto);
        projectRepository.delete(project);
        return id;
    }

    private Project findProjectById(Long id, IllegalArgumentException no_such_project) {
        Project project = projectRepository.findById(id).orElseThrow(() -> no_such_project);
        return project;
    }

    //check if person is owner of this project post and if he/she is authorised to edit
    private void checkEditPermission(Project project, MemberDto memberDto){
        Long ownerId = project.getMember_id();
        if(ownerId!= memberDto.getMemberId()){
            throw new IllegalArgumentException("you are not the owner of this post");
        }
    }

    //check existing, duplicate project via title
    private void validateDuplicateProject(ProjectCreateDto projectCreateDto){
        List<Project> findProjects = projectRepository.findByTitle(projectCreateDto.getProjectDto().getTitle());
        if(!findProjects.isEmpty()){
            throw new DuplicateKeyException("already an existing project");
        }
    }
}
