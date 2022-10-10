package com.gbc.codingmates.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthTokenRepository extends JpaRepository<OAuthToken, String>,
    OAuthTokenCustomRepository {

}
