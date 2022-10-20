package com.gbc.codingmates.service.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.dto.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;

//    Create project
    @Transactional
    public ResponseEntity<Long> saveProject(final ProjectDto ProjectDto){
        validateDuplicateProject(ProjectDto);
        Project project = projectRepository.save(ProjectDto.toEntity());
//        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
        return ResponseEntity.ok(project.getId());
    }

    //list all projects
    public List<Project> listAll(){
//        return projectRepository.findAll();
//        List<Project> projects = em.createQuery(
//                "select p from Project p" +
//                        " join fetch p.member m", Project.class
//        ).getResultList();
//        return projects;
        return projectRepository.listAllWithMember();
    }

    public ResponseEntity<ProjectDto> findById(final ProjectDto projectDto){
        Project project = projectRepository.findById(projectDto.getId()).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok(projectDto);
    }

    //update/edit project
    @Transactional
    public ResponseEntity<Long> edit(final MemberDto memberDto, final Long id, final ProjectDto ProjectDto){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        Long ownerId = project.getId();
        if(ownerId!=memberDto.getMemberId()){
//            throw HttpClientErrorException.Forbidden;
//            return ResponseEntity.badRequest(memberDto.getMemberId());
        }
        project.update(ProjectDto.getTitle(), ProjectDto.getContent());
        return ResponseEntity.ok(id);
    }

    //delete project
    @Transactional
    public ResponseEntity<Long> deleteById(final Long id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such project"));
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
