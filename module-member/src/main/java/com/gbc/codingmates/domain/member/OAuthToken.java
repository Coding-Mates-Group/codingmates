package com.gbc.codingmates.domain.member;

import com.gbc.codingmates.domain.BaseTimeEntity;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
public class OAuthToken extends BaseTimeEntity {

    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();

    private String authUserId;

    @Enumerated(EnumType.STRING)
    private OAuthType oAuthType;

    private String email;

}
