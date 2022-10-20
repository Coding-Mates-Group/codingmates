package com.gbc.codingmates.service.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

//    Create project
    @Transactional
    public ResponseEntity<Long> saveProject(final ProjectDto projectDto){
        validateDuplicateProject(projectDto);
        Project project = projectRepository.save(modelMapper.map(projectDto,Project.class));
//        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
        return ResponseEntity.ok(project.getId());
    }

    //list all projects
//    public List<Project> listAll(){
////        return projectRepository.findAll();
////        List<Project> projects = em.createQuery(
////                "select p from Project p" +
////                        " join fetch p.member m", Project.class
////        ).getResultList();
////        return projects;
////        return projectRepository.listAllWithMember();
//        return projectRepository.findAll();
//    }

    public ResponseEntity<List<ProjectDto>> listAll(){
        List<Project> projectList = projectRepository.findAll();
        return ResponseEntity.ok(projectList.stream()
                .map(project -> modelMapper.map(project,ProjectDto.class))
                .collect(Collectors.toList()));
    }


    public ResponseEntity<ProjectDto> findById(final ProjectDto projectDto){
        Project project = projectRepository.findById(projectDto.getId()).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok(modelMapper.map(project,ProjectDto.class));
    }

    //update/edit project
    @Transactional
    public ResponseEntity<Long> edit(final MemberDto memberDto, final Long id, final ProjectDto ProjectDto) throws AccessDeniedException {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        Long ownerId = project.getMember_id();
        if(ownerId!=memberDto.getMemberId()){
            throw new AccessDeniedException("you are not the owner of this post");
        }
        project.update(ProjectDto.getTitle(), ProjectDto.getContent());
        return ResponseEntity.ok(id);
    }

    //delete project
    @Transactional
    public ResponseEntity<Long> deleteById(final MemberDto memberDto, final Long id) throws AccessDeniedException {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such project"));
        Long ownerId = project.getMember_id();
        if(ownerId!=memberDto.getMemberId()){
            throw new AccessDeniedException("you are not the owner of this post");
        }
        projectRepository.delete(project);
        return ResponseEntity.ok(id);
    }

    //to be changed to ResponseEntity
    //check existing, duplicate project
    private void validateDuplicateProject(ProjectDto ProjectDto){
        List<Project> findProjects = projectRepository.findByTitle(ProjectDto.getTitle());
        if(!findProjects.isEmpty()){
            throw new IllegalStateException("already an existing project");
        }
    }

}
