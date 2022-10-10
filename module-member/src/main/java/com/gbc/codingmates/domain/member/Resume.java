package com.gbc.codingmates.domain.member;

import static lombok.AccessLevel.PROTECTED;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Resume {

    private String gitRepository;
    private String portFolio;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Resume)) {
            return false;
        }
        Resume resume = (Resume) o;
        return Objects.equals(gitRepository, resume.gitRepository)
            && Objects.equals(portFolio, resume.portFolio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gitRepository, portFolio);
    }
}
