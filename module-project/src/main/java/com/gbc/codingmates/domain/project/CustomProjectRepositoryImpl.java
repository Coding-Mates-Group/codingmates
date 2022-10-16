package com.gbc.codingmates.domain.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomProjectRepositoryImpl implements CustomProjectRepository{

    private final EntityManager em;

    @Override
    public List<Project> listAllWithMember() {
        return em.createQuery("select p from Project p" +
                        " join fetch p.member m", Project.class)
                .getResultList();
    }
}
