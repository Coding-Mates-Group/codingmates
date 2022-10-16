package com.gbc.codingmates.domain.project;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomProjectRepositoryImpl implements CustomProjectRepository{

    private final EntityManager em;

    @Override
    public List<Project> findAllWithMember() {
        List<Project> resultList = em.createQuery(
                "select p from Project p join fetch member m on p.member_id=m.id", Project.class)
            .getResultList();
        return resultList;
    }
}
