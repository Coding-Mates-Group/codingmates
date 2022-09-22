package com.gbc.codingmates.domain.member;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OAuthTokenCustomRepositoryImpl implements OAuthTokenCustomRepository {

    private final EntityManager em;

    @Override
    public Optional<OAuthToken> findByIdWithLock(String id) {
        return Optional.of(
            em.createQuery("select ot from OAuthToken ot where ot.id=:id", OAuthToken.class)
                .setParameter("id", id)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult());
    }
}
