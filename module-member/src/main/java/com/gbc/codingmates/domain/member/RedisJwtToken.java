package com.gbc.codingmates.domain.member;

import com.gbc.codingmates.dto.member.MemberDto;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("JWTTOKEN") // Jpa의 Entity에 해당하는 Annotation. redis에 저장시 key로 value값을 앞에 넣는다. JWTTOKEN+key
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisJwtToken {
    @Id // JWTTOKEN+id
    private String id;

//    @Indexed// 필드 값으로 데이터 찾을수 있게 하는 annotation
//    private String token;

    private MemberDto data;
    @TimeToLive //유호시간. 초단위 유효시간이 지나면 자동 삭제
    private Long expiration; // seconds
}
