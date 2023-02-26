package com.gbc.codingmates.domain.member;

import org.springframework.data.repository.CrudRepository;

public interface RedisJwtTokenRepository extends CrudRepository<RedisJwtToken, String> {
//    @Cacheable(value = "redisJwtToken", key = "#p0", unless = "#result == null")
//    Optional<RedisJwtToken> findByToken(String token);
}
