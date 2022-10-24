package com.gbc.codingmates.domain.bookmark;

import com.gbc.codingmates.domain.project.Project;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @JoinColumn(name = "member_id")
    private Long member_id;

    @Column(nullable = false)
    private Boolean accept_info;
}
