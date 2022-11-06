package com.gbc.codingmates.domain.member;

import org.springframework.data.repository.CrudRepository;

public interface OAuthTokenRepository extends CrudRepository<OAuthToken, String> {

}
