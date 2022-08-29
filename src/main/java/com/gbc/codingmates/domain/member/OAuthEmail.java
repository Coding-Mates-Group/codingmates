package com.gbc.codingmates.domain.member;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthEmail {

    private String emailGoogle;
    private String emailGithub;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OAuthEmail)) {
            return false;
        }
        OAuthEmail that = (OAuthEmail) o;
        return Objects.equals(emailGoogle, that.emailGoogle) && Objects.equals(
            emailGithub, that.emailGithub);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailGoogle, emailGithub);
    }
}
