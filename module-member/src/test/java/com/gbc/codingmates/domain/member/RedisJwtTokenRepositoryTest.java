package com.gbc.codingmates.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "member-test"})
@DisplayName("Redis jwt token Test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedisJwtTokenRepositoryTest {

    @Autowired
    private RedisJwtTokenRepository redisJwtTokenRepository;

    @BeforeEach
    public void init(){
        redisJwtTokenRepository.deleteAll();
    }

    @Test
    @DisplayName("redis jwt token cache 정상 동작")
    public void normal() {
        //given
        RedisJwtToken redisJwtTo = redisJwtTokenRepository.save(
            new RedisJwtToken("redis-id", null, 1000L));

        //when
        Optional<RedisJwtToken> redisJwtToken = redisJwtTokenRepository.findById("redis-id");

        //then
        assertAll(
            () -> assertThat(redisJwtToken.isPresent()).isTrue(),
            () -> assertThat(redisJwtToken.get().getId()).isEqualTo("redis-id")
        );
    }

    @Test
    public void redisCacheNotExist(){
        //when
        Optional<RedisJwtToken> redisJwtToken = redisJwtTokenRepository.findById("redis-id");
        //then
        assertThat(redisJwtToken.isPresent()).isFalse();
    }
}