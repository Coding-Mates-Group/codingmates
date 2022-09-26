package com.gbc.codingmates.domain.member;

import java.util.Optional;

public interface OAuthTokenCustomRepository {

    Optional<OAuthToken> findByIdWithLock(String id);
}
