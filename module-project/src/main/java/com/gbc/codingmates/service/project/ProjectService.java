package com.gbc.codingmates.service.project;

import com.gbc.codingmates.domain.project.Project;
import com.gbc.codingmates.domain.project.ProjectRepository;
import com.gbc.codingmates.dto.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EntityManager em;

//    Create project
    @Transactional
    public ResponseEntity<Long> save(final ProjectDto ProjectDto){
        validateDuplicateProject(ProjectDto);
        Project project = projectRepository.save(ProjectDto.toEntity());
//        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
        return ResponseEntity.ok(project.getId());
    }

    //list all projects
    public List<Project> listAll(){
        List<Project> projects = em.createQuery(
                "select p from Project p" +
                        " join fetch p.member m", Project.class
        ).getResultList();
        return projects;
    }

    public ResponseEntity<Long> findById(final ProjectDto ProjectDto){
        Project project = projectRepository.findById(ProjectDto.getId()).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok(project.getId());
    }

    //update/edit project
    @Transactional
    public ResponseEntity<Long> update(final Long id, final ProjectDto ProjectDto){
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
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
