package com.gbc.codingmates.domain.member;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gbc.codingmates.domain.BaseTimeEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class OAuth extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "oauth_id")
    private Long id;

    @Column(nullable = false)
    private String authId;

    @Enumerated(STRING)
    private OAuthType provider;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_oauth_member"))
    private Member member;

    public static OAuth of(OAuthToken oAuthToken, Member member) {
        return OAuth.builder()
            .authId(oAuthToken.getAuthUserId())
            .member(member)
            .provider(oAuthToken.getOAuthType())
            .build();
    }

    @Builder
    public OAuth(String authId, OAuthType provider, Member member) {
        this.authId = authId;
        this.provider = provider;
        this.member = member;
    }
}
