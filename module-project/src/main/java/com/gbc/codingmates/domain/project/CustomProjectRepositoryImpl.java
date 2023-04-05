package com.gbc.codingmates.domain.project;

import com.gbc.codingmates.dto.project.ProjectDto;
import com.gbc.codingmates.dto.project.response.ProjectSelectOneResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomProjectRepositoryImpl implements CustomProjectRepository{

    private final EntityManager em;
//    private final JPAQueryFactory query;

    public List<ProjectDto> listAllWithMember() {
//        return em.createQuery("select p from Project p" +
//                        " join fetch p.member_id m", ProjectDto.class)
//                .getResultList();
        return null;
    }

    @Override
    public ProjectSelectOneResponseDto listOne() {
//        query.selectFrom()
        return null;
    }
}
