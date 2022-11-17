package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.dto.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomProjectRepositoryImpl implements CustomProjectRepository{

    private final EntityManager em;

    public List<ProjectDto> listAllWithMember() {
        return em.createQuery("select p from Project p" +
                        " join fetch p.member_id m", ProjectDto.class)
                .getResultList();
    }
}
