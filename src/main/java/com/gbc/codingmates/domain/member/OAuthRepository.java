package com.gbc.codingmates.domain.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {

    Optional<OAuth> findByAuthIdAndProvider(String authId, OAuthType provider);
}
