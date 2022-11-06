package com.gbc.codingmates.domain.member;

import com.gbc.codingmates.domain.BaseTimeEntity;
import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("OAUTHTOKEN")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
public class OAuthToken{

    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();

    private String authUserId;

    @Enumerated(EnumType.STRING)
    private OAuthType oAuthType;

    private String email;

    @TimeToLive
    @Builder.Default//유호시간. 초단위 유효시간이 지나면 자동 삭제
    private Long expiration = 60L * 5; // seconds

}
