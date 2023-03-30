package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.dto.ProjectDto;
import com.gbc.codingmates.dto.project.ProjectResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomProjectRepositoryImpl implements CustomProjectRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public List<ProjectDto> listAllWithMember() {
        return em.createQuery("select p from Project p" +
                        " join fetch p.member_id m", ProjectDto.class)
                .getResultList();
    }

    @Override
    public ProjectResponseDto listOne() {
//        query.selectFrom()
        return null;
    }
}
